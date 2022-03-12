package com.vreal.domain.repository.centro.nucleo;

import com.vreal.domain.model.centro.model.Nucleo;

import com.vreal.domain.model.centro.model.Nucleo_;
import com.vreal.domain.model.centro.model.Regiao_;
import com.vreal.domain.repository.centro.filter.NucleoFilter;
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

public class NucleoRepositoryImpl  implements NucleoRepositoryQuery{

    @Autowired
    private EntityManager manager;

    @Override
    public Page<Nucleo> filtrar(NucleoFilter nucleoFilter, Pageable pageable) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Nucleo> criteria = builder.createQuery(Nucleo.class);
        Root<Nucleo> root = criteria.from(Nucleo.class);

        //criar restrições
        Predicate[] predicates = criarRestricoes(nucleoFilter,builder,root);
        criteria.where(predicates);

        TypedQuery<Nucleo> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query,pageable);

        return new PageImpl<>(query.getResultList(),pageable,total(nucleoFilter)) ;
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<Nucleo> query, Pageable pageable) {
        int paginalAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginalAtual*totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }

    private Long total(NucleoFilter nucleoFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Nucleo> root = criteria.from(Nucleo.class);

        //criar restrições
        Predicate[] predicates = criarRestricoes(nucleoFilter,builder,root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        TypedQuery<Long> query = manager.createQuery(criteria);

        return query.getSingleResult();

    }

    private Predicate[] criarRestricoes(NucleoFilter nucleoFilter, CriteriaBuilder builder, Root<Nucleo> root) {

        List<Predicate> predicates = new ArrayList<>();

        if ( nucleoFilter.getNome() != null && !nucleoFilter.getNome().isBlank() ){
            predicates.add(builder.like(builder.lower(root.get(Nucleo_.nome)), "%"+nucleoFilter.getNome().toLowerCase()+"%"));
        }

        if (nucleoFilter.getIdRegiao() != null ){
            System.out.println(nucleoFilter.getIdRegiao());
            predicates.add(builder.equal((root.get(Nucleo_.regiao).get(Regiao_.id)), nucleoFilter.getIdRegiao()));
        }

        return predicates.toArray(new Predicate[predicates.size()]);

    }
}
