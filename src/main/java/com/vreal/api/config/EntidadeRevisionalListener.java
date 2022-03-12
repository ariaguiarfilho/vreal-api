package com.vreal.api.config;

import org.hibernate.envers.RevisionListener;

public class EntidadeRevisionalListener implements RevisionListener {
    @Override
    public void newRevision(Object revisionEntity) {
        String login = "teste";
        EntidadeRevisional exampleRevEntity = (EntidadeRevisional) revisionEntity;
        exampleRevEntity.setNomeUsuario(login);
    }
}
