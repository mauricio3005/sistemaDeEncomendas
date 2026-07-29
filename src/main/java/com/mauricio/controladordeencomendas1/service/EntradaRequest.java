package com.mauricio.controladordeencomendas1.service;

public record EntradaRequest(Long ownerId, String sender, String description) {
}
