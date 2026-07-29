package com.mauricio.controladordeencomendas1.service;

import com.mauricio.controladordeencomendas1.model.Encomenda;
import com.mauricio.controladordeencomendas1.model.StatusEncomenda;
import com.mauricio.controladordeencomendas1.model.User;
import com.mauricio.controladordeencomendas1.repository.EncomendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EncomendaService {

    private final EncomendaRepository encomendaRepository;

    public EncomendaService(EncomendaRepository encomendaRepository) {
        this.encomendaRepository = encomendaRepository;
    }

    public Encomenda findById(Long id) {
        return encomendaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Encomenda nao encontrada"));
    }

    public List<Encomenda> listPending(User owner) {
        return encomendaRepository.findByOwnerAndStatus(owner, StatusEncomenda.AGUARDANDO_RETIRADA);
    }

    public List<Encomenda> listHistory(User owner) {
        return encomendaRepository.findByOwnerAndStatus(owner, StatusEncomenda.RETIRADA);
    }

    public List<Encomenda> listByOwnerId(Long ownerId) {
        return encomendaRepository.findByOwnerId(ownerId);
    }

    public List<Encomenda> listByRegisteredById(Long registeredById) {
        return encomendaRepository.findByRegisteredById(registeredById);
    }

    public void delete(Long id) {
        Encomenda encomenda = findById(id);

        if (encomenda.getStatus() != StatusEncomenda.AGUARDANDO_RETIRADA) {
            throw new IllegalStateException("Nao e possivel excluir uma encomenda ja retirada");
        }

        encomendaRepository.delete(encomenda);
    }
}
