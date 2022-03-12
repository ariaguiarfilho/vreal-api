package com.vreal.api.event.listener;

import com.vreal.api.event.RecursoCriadoEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {

    @Override
    public void onApplicationEvent(RecursoCriadoEvent recursoCriadoEvent) {

        HttpServletResponse response = recursoCriadoEvent.getResponse();
        String codigo = recursoCriadoEvent.getCodigo();

        adicionarHeaderLocation(response,codigo);
    }

    private void adicionarHeaderLocation(HttpServletResponse response, String codigo) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(codigo).toUri();
        response.setHeader("Locations",uri.toASCIIString());
    }
}
