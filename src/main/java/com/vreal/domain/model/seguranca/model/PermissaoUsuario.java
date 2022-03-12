package com.vreal.domain.model.seguranca.model;

import com.vreal.domain.model.seguranca.enumeration.NivelAcesso;
import com.vreal.domain.model.seguranca.enumeration.Tarefa;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "permissao_usuario", schema = "seguranca", uniqueConstraints = @UniqueConstraint(columnNames = {"usr_id", "pep_tarefa"}))
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Setter
@Getter
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissaoUsuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pru_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "usr_id", referencedColumnName = "usr_id", nullable = false)
    private Usuario usuario;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "pru_nivel", nullable = false)
    private NivelAcesso nivelAcesso;

    @NotNull
    @Column(name = "pep_tarefa",nullable = false)
    @Enumerated(EnumType.STRING)
    private Tarefa tarefa;

    //inclui o offset na data
//    private OffsetDateTime teste

}
