package com.mauricio.controladordeencomendas1.controller;

import com.mauricio.controladordeencomendas1.model.Encomenda;
import com.mauricio.controladordeencomendas1.security.CustomUserDetails;
import com.mauricio.controladordeencomendas1.service.EncomendaService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/minhas-encomendas")
public class MinhasEncomendasController {

    private final EncomendaService encomendaService;

    public MinhasEncomendasController(EncomendaService encomendaService) {
        this.encomendaService = encomendaService;
    }

    @GetMapping("/pendentes")
    public List<Encomenda> pendentes(@AuthenticationPrincipal CustomUserDetails principal) {
        return encomendaService.listPending(principal.getUser());
    }

    @GetMapping("/historico")
    public List<Encomenda> historico(@AuthenticationPrincipal CustomUserDetails principal) {
        return encomendaService.listHistory(principal.getUser());
    }
}
