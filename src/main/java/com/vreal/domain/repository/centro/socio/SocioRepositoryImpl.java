package com.vreal.domain.repository.centro.socio;


import com.vreal.domain.model.centro.model.Nucleo_;
import com.vreal.domain.model.centro.model.Regiao_;
import com.vreal.domain.model.centro.model.Socio;

import com.vreal.domain.model.centro.model.Socio_;
import com.vreal.api.model.centro.SocioDTO;
import com.vreal.domain.repository.centro.filter.SocioFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class SocioRepositoryImpl implements SocioRepositoryQuery {


    @Autowired
    private EntityManager manager;

    @Override
    public Page<Socio> filtrar(SocioFilter socioFilter, Pageable pageable) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Socio> criteria = builder.createQuery(Socio.class);
        Root<Socio> root = criteria.from(Socio.class);

        //criar restrições
        Predicate[] predicates = criarRestricoes(socioFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Socio> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(socioFilter));
    }

    @Override
    public Page<SocioDTO> consultar(SocioFilter socioFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<SocioDTO> criteria = builder.createQuery(SocioDTO.class);
        Root<Socio> root = criteria.from(Socio.class);

//
//        Long id, GrauSocio grauSocio, StatusSocio statusSocio, boolean ativo,
//        String nomeUsual, Long idNucleo, String nomeNucleo, String regiao,
//                String nomeResponsvel, Long idResponsvel, String cpf,
//                String identificadorBoleto, Integer identificadorReuni, boolean gerarBoleto

        criteria.select(builder.construct(SocioDTO.class,
                root.get(Socio_.id),
                root.get(Socio_.grauSocio),
                root.get(Socio_.statusSocio),
                root.get(Socio_.ativo),
                root.get(Socio_.nomeUsual),

                root.get(Socio_.nucleo).get(Nucleo_.id),
                root.get(Socio_.nucleo).get(Nucleo_.nome),
                root.get(Socio_.nucleo).get(Nucleo_.regiao).get(Regiao_.descricao),

                root.get(Socio_.cpf),
                root.get(Socio_.identificadorBoleto),
                root.get(Socio_.identificadorReuni),
                root.get(Socio_.gerarBoleto)
        ));

        //criar restrições
        Predicate[] predicates = criarRestricoes(socioFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<SocioDTO> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(socioFilter));

    }

    private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
        int paginalAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginalAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }

    private Long total(SocioFilter socioFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Socio> root = criteria.from(Socio.class);

        //criar restrições
        Predicate[] predicates = criarRestricoes(socioFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        TypedQuery<Long> query = manager.createQuery(criteria);

        return query.getSingleResult();

    }

    private Predicate[] criarRestricoes(SocioFilter socioFilter, CriteriaBuilder builder, Root<Socio> root) {

        List<Predicate> predicates = new ArrayList<>();

        if (socioFilter.getNome() != null && !socioFilter.getNome().isBlank()) {
            predicates.add(builder.like(builder.lower(root.get(Socio_.nomeUsual)), "%" + socioFilter.getNome().toLowerCase() + "%"));
        }
        if (socioFilter.getCpf() != null && !socioFilter.getCpf().isBlank()) {
            predicates.add(builder.like(root.get(Socio_.cpf), "%" + socioFilter.getCpf() + "%"));
        }

        if (socioFilter.getIdNucleo() != null) {
            System.out.println(socioFilter.getIdNucleo());
            predicates.add(builder.equal((root.get(Socio_.nucleo).get(Nucleo_.id)), socioFilter.getIdNucleo()));
        }

        return predicates.toArray(new Predicate[predicates.size()]);

    }


}
