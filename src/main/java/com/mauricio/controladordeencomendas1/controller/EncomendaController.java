package com.mauricio.controladordeencomendas1.controller;

import com.mauricio.controladordeencomendas1.model.Encomenda;
import com.mauricio.controladordeencomendas1.security.CustomUserDetails;
import com.mauricio.controladordeencomendas1.service.EncomendaService;
import com.mauricio.controladordeencomendas1.service.EntradaRequest;
import com.mauricio.controladordeencomendas1.service.EntradaService;
import com.mauricio.controladordeencomendas1.service.RetiradaService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/encomendas")
public class EncomendaController {

    private final EncomendaService encomendaService;
    private final EntradaService entradaService;
    private final RetiradaService retiradaService;

    public EncomendaController(EncomendaService encomendaService, EntradaService entradaService,
                                RetiradaService retiradaService) {
        this.encomendaService = encomendaService;
        this.entradaService = entradaService;
        this.retiradaService = retiradaService;
    }

    @PostMapping
    public Encomenda registrarEntrada(@RequestBody EntradaRequest request,
                                       @AuthenticationPrincipal CustomUserDetails principal) {
        return entradaService.registrar(request, principal.getUser());
    }

    @PostMapping("/{id}/retirada")
    public Encomenda confirmarRetirada(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails principal) {
        return retiradaService.confirmar(id, principal.getUser());
    }

    @GetMapping("/{id}")
    public Encomenda buscarPorId(@PathVariable Long id) {
        return encomendaService.findById(id);
    }

    @GetMapping("/registradas-por-mim")
    public List<Encomenda> listarRegistradasPorMim(@AuthenticationPrincipal CustomUserDetails principal) {
        return encomendaService.listByRegisteredById(principal.getUser().getId());
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        encomendaService.delete(id);
    }
}
