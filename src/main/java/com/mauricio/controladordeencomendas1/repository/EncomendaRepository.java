package com.mauricio.controladordeencomendas1.repository;

import com.mauricio.controladordeencomendas1.model.Encomenda;
import com.mauricio.controladordeencomendas1.model.StatusEncomenda;
import com.mauricio.controladordeencomendas1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EncomendaRepository extends JpaRepository<Encomenda, Long> {

    List<Encomenda> findByOwnerAndStatus(User owner, StatusEncomenda status);

    List<Encomenda> findByRegisteredById(Long registeredById);

    List<Encomenda> findByOwnerId(Long ownerId);
}
