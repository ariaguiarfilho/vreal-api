package com.vreal.api.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

public class RecursoCriadoEvent extends ApplicationEvent {

    @Getter
    private HttpServletResponse response;
    @Getter
    private String codigo;

    public RecursoCriadoEvent(Object source, HttpServletResponse response, String codigo) {
        super(source);
        this.response = response;
        this.codigo = codigo;
    }

}
