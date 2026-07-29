package com.mauricio.controladordeencomendas1.service;

import com.mauricio.controladordeencomendas1.model.Encomenda;
import com.mauricio.controladordeencomendas1.model.Role;
import com.mauricio.controladordeencomendas1.model.StatusEncomenda;
import com.mauricio.controladordeencomendas1.model.User;
import com.mauricio.controladordeencomendas1.repository.EncomendaRepository;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RetiradaService {

    private final EncomendaRepository encomendaRepository;

    public RetiradaService(EncomendaRepository encomendaRepository) {
        this.encomendaRepository = encomendaRepository;
    }

    @Transactional
    public Encomenda confirmar(Long encomendaId, User receivedBy) {
        if (receivedBy.getRole() != Role.PORTEIRO) {
            throw new IllegalArgumentException("Somente um porteiro pode confirmar a retirada de uma encomenda");
        }

        Encomenda encomenda = encomendaRepository.findById(encomendaId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Encomenda nao encontrada"));

        if (encomenda.getStatus() == StatusEncomenda.RETIRADA) {
            throw new EncomendaJaRetiradaException("Esta encomenda ja foi retirada");
        }

        encomenda.setReceivedBy(receivedBy);
        encomenda.setReceivedAt(LocalDateTime.now());
        encomenda.setStatus(StatusEncomenda.RETIRADA);

        try {
            return encomendaRepository.saveAndFlush(encomenda);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new EncomendaJaRetiradaException("Esta encomenda acabou de ser retirada por outro porteiro");
        }
    }
}
