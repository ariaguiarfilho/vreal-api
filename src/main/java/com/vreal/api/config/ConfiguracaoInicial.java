package com.vreal.api.config;

import com.vreal.domain.service.seguranca.PermissaoPerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@Configuration
public class ConfiguracaoInicial {

    @Autowired
    private PermissaoPerfilService permissaoPerfilService;

    //ContextRefreshedEvent ele é executado antes da aplicação começar a aceitar requests
    @EventListener(ContextRefreshedEvent.class)
    public void iniciando(){
        System.out.println("======================================================================");
        System.out.println("                    INCIANDO CONFIGURAÇÕES                    ");
        System.out.println("======================================================================");
        permissaoPerfilService.carregarPermissoesDoPerfil();
        System.out.println("======================================================================");
        System.out.println("                    FINALIZANDO CONFIGURAÇÕES                    ");
        System.out.println("======================================================================");


    }
}
