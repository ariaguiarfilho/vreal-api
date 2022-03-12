package com.vreal.domain.model.centro.model;

import com.vreal.domain.model.centro.enumeration.GrauSocio;
import com.vreal.domain.model.centro.enumeration.StatusSocio;
import com.vreal.domain.model.utilitarios.ValidationGroups;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.io.Serializable;

@Entity
@Table(name = "socio", schema = "centro")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Audited()
@AuditTable(schema = "auditoria", value = "socio_aud")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Socio  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "soc_id", nullable = false)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "soc_gra_id", nullable = false)
    private GrauSocio grauSocio;

    @Enumerated(EnumType.STRING)
    @Column(name = "soc_status")
    private StatusSocio statusSocio;

    @Column(name = "soc_ativo", nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private boolean ativo;

    @NotBlank
    @Column(name = "soc_nome", nullable = false)
    private String nomeUsual;

    @Column(name = "soc_observacao", length = 1024)
    private String observacao;

    @Valid // valida o que está dentro do nucleo tbm
    @ConvertGroup(from = Default.class, to = ValidationGroups.NucleoId.class)// converte o tipo de validação do default para o nucleoid
    @ManyToOne
    @JoinColumn(name = "nuc_id", referencedColumnName = "nuc_id", nullable = false)
    private Nucleo nucleo;

    @ManyToOne
    @JoinColumn(name = "soc_responsavel_id", referencedColumnName = "soc_id")
    private Socio responsavel;

    @NotBlank
    @Column(name = "pes_cpf")
    private String cpf;

    @Column(name = "soc_url_foto")
    private String urlFoto;

    @Column(name = "soc_identificador_boleto")
    private String identificadorBoleto;

    @Column(name = "soc_identificador_reuni")
    private Integer identificadorReuni;

    @Column(name = "soc_gerar_boleto", columnDefinition = "BOOLEAN DEFAULT true")
    private boolean gerarBoleto;
}
