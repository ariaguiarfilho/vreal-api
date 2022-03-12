package com.vreal.domain.model.centro.model;

import com.vreal.domain.model.utilitarios.ValidationGroups;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "nucleo", schema = "centro")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@AuditTable(schema = "auditoria", value = "nucleo_aud")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Nucleo implements Serializable {


    @NotNull(groups = ValidationGroups.NucleoId.class) // valida
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nuc_id", nullable = false)
    private Long id;

    @Column(name = "nuc_id_reuni", unique = true)
    private Long idReuni;

    @NotEmpty
    @Column(name = "nuc_nome", unique = true, nullable = false)
    private String nome;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "reg_id", nullable = false, referencedColumnName = "reg_id")
    private Regiao regiao;

    @Column(name = "nuc_nome_representante")
    private String nomeRepresentante;

    @Column(name = "nuc_telefone_representante")
    private String telefoneRepresentante;

    @Column(name = "nuc_celular_representante")
    private String celularRepresentante;

    @Column(name = "nuc_cnpj")
    private String cnpj;

    @Email
    @Column(name = "nuc_email_tesouraria")
    private String emailTesouraria;

    @Column(name = "nuc_token_teste")
    private String tokenTeste;

    @Column(name = "nuc_token_producao")
    private String tokenProducao;

    @Column(name = "nuc_senha_portal")
    private String senhaPortal;

    @Column(name = "nuc_ativo_boleto", columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean ativarBoleto;

    @Column(name = "nuc_ativo_sistema", columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean ativarSistema;

    @Column(name = "nuc_ativo_portal", columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean ativarPortal;

    @Column(name = "nuc_taxa_boleto")
    private BigDecimal valorTaxaBoleto;

    public Nucleo() {
        this.ativarBoleto = false;
        this.ativarSistema = true;
        this.ativarPortal = false;
        this.valorTaxaBoleto = BigDecimal.ZERO;
    }
}
