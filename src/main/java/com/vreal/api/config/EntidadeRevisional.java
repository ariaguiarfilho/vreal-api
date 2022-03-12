package com.vreal.api.config;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "entidade_revisional", schema = "auditoria")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@RevisionEntity(EntidadeRevisionalListener.class)
public class EntidadeRevisional  {

    @Id
    @GeneratedValue
    @RevisionNumber
    @Column(name = "enr_id", nullable = false)
    private Long id;

    @RevisionTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "enr_data", nullable = false)
    private Date data;

    @Column(name = "enr_usuario", nullable = false)
    private String nomeUsuario;
}
