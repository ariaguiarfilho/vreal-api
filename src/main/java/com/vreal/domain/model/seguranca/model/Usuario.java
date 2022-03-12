package com.vreal.domain.model.seguranca.model;

import com.vreal.domain.model.centro.model.Nucleo;
import com.vreal.domain.model.centro.model.Socio;
import com.vreal.domain.model.seguranca.enumeration.Perfil;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "usuario", schema = "seguranca")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@AuditTable(schema = "auditoria", value = "usuario_aud")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id", nullable = false)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank
    @Length(min = 4)
    @Column(name = "usr_login", nullable = false, unique = true)
    private String login;

    @NotBlank
    @Length(min = 6)
    @Column(name = "usr_senha", nullable = false)
    private String senha;

    @NotNull
    @Column(name = "usr_Ativo", nullable = false, columnDefinition = "boolean default true")
    private boolean ativo;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "usr_perfil", nullable = false)
    private Perfil perfil;


    @ManyToOne
    @JoinColumn(name = "soc_id", referencedColumnName = "soc_id", nullable = false, unique = true,foreignKey = @ForeignKey(name = "FK_USUARIO_SOCIO"))
    private Socio socio;

    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(schema = "seguranca", name = "nucleo_usuario", joinColumns = {
            @JoinColumn(name = "usr_id")}, inverseJoinColumns = {
            @JoinColumn(name = "nuc_id")})
    @AuditJoinTable(schema = "auditoria", name = "nucleo_usuario_aud")
    private List<Nucleo> nucleos;
}
