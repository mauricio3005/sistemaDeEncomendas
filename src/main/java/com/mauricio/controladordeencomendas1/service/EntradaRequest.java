package com.mauricio.controladordeencomendas1.service;

public record EntradaRequest(Long ownerId, Long registeredById, String sender, String description) {
}
