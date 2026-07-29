package com.mauricio.controladordeencomendas1.service;

import com.mauricio.controladordeencomendas1.model.Role;

public record RegisterRequest(String name, String email, String password, Role role) {
}
