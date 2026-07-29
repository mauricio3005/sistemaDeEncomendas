package com.mauricio.controladordeencomendas1.config;

import com.mauricio.controladordeencomendas1.model.Role;
import com.mauricio.controladordeencomendas1.model.User;
import com.mauricio.controladordeencomendas1.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {

    private static final String EMAIL_PORTEIRO_INICIAL = "porteiro@condominio.com";
    private static final String SENHA_PORTEIRO_INICIAL = "troque-esta-senha";

    @Bean
    public CommandLineRunner seedPorteiroInicial(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!userRepository.existsByEmail(EMAIL_PORTEIRO_INICIAL)) {
                User porteiro = new User(null, Role.PORTEIRO, "Porteiro Inicial",
                        EMAIL_PORTEIRO_INICIAL, passwordEncoder.encode(SENHA_PORTEIRO_INICIAL));
                userRepository.save(porteiro);
            }
        };
    }
}
