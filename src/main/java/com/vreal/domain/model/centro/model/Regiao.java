package com.vreal.domain.model.centro.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Entity
@Table(name = "regiao", schema = "centro")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@AuditTable(schema = "auditoria", value = "regiao_aud")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Regiao implements Serializable {
    @Id
    @Column(name = "reg_id", nullable = false)
//    @Setter(AccessLevel.NONE)
    private Integer id;

    @Column(name = "reg_id_reuni",unique = true)
    private Integer idReuni;

    @Column(name = "reg_descricao")
    private String descricao;

    @Column(name = "reg_nome_central")
    private String nomeCentral;

    @Column(name = "reg_telefone_central")
    private String telefoneCentral;

    @Column(name = "reg_celular_central")
    private String celularCentral;

    @Email
    @Column(name = "reg_email_central")
    private String emailCentral;
}
