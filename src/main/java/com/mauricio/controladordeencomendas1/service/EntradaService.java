package com.mauricio.controladordeencomendas1.service;

import com.mauricio.controladordeencomendas1.model.Encomenda;
import com.mauricio.controladordeencomendas1.model.Role;
import com.mauricio.controladordeencomendas1.model.StatusEncomenda;
import com.mauricio.controladordeencomendas1.model.User;
import com.mauricio.controladordeencomendas1.repository.EncomendaRepository;
import com.mauricio.controladordeencomendas1.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EntradaService {

    private final EncomendaRepository encomendaRepository;
    private final UserRepository userRepository;

    public EntradaService(EncomendaRepository encomendaRepository, UserRepository userRepository) {
        this.encomendaRepository = encomendaRepository;
        this.userRepository = userRepository;
    }

    public Encomenda registrar(EntradaRequest request) {
        User owner = userRepository.findById(request.ownerId())
                .orElseThrow(() -> new IllegalArgumentException("Morador nao encontrado"));

        if (owner.getRole() != Role.MORADOR) {
            throw new IllegalArgumentException("A encomenda precisa ser destinada a um morador");
        }

        User registeredBy = userRepository.findById(request.registeredById())
                .orElseThrow(() -> new IllegalArgumentException("Porteiro nao encontrado"));

        if (registeredBy.getRole() != Role.PORTEIRO) {
            throw new IllegalArgumentException("Somente um porteiro pode registrar a chegada de uma encomenda");
        }

        Encomenda encomenda = new Encomenda(null, owner, registeredBy, null, request.sender(), request.description(),
                LocalDateTime.now(), null, StatusEncomenda.AGUARDANDO_RETIRADA, null);

        return encomendaRepository.save(encomenda);
    }
}
