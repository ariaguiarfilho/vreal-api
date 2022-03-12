package com.vreal.domain.model.seguranca.model;

import com.vreal.domain.model.seguranca.enumeration.NivelAcesso;
import com.vreal.domain.model.seguranca.enumeration.Perfil;
import com.vreal.domain.model.seguranca.enumeration.Tarefa;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "permissao_perfil",schema = "seguranca",uniqueConstraints = @UniqueConstraint(columnNames = {"pep_perfil","pep_tarefa"}))
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Setter
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class PermissaoPerfil implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pep_id",nullable = false)
    private Long id;

    @NotNull
    @Column(name = "pep_perfil",nullable = false)
    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    @NotNull
    @Column(name = "pep_nivel",nullable = false)
    @Enumerated(EnumType.STRING)
    private NivelAcesso nivelAcesso;

    @NotNull
    @Column(name = "pep_tarefa",nullable = false)
    @Enumerated(EnumType.STRING)
    private Tarefa tarefa;

    public PermissaoPerfil(Perfil perfil, NivelAcesso nivelAcesso, Tarefa tarefa) {
        this.perfil = perfil;
        this.nivelAcesso = nivelAcesso;
        this.tarefa = tarefa;
    }
}
