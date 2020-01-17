package app.openpayd.foreignexchange.service;

import app.openpayd.foreignexchange.repository.entity.CurrencyConversionEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JpaCurrencyConversionPredicateBuilder {
    private static final String CREATED_AT = "createdAt";

    private List<Predicate> predicateList;
    private CriteriaBuilder criteriaBuilder;
    private Root<CurrencyConversionEntity> currencyConversionEntityRoot;

    public JpaCurrencyConversionPredicateBuilder(CriteriaBuilder criteriaBuilder,
                                                 Root<CurrencyConversionEntity> currencyConversionEntityRoot) {
        this.criteriaBuilder = criteriaBuilder;
        this.currencyConversionEntityRoot = currencyConversionEntityRoot;
        this.predicateList = new ArrayList<>();
    }

    public JpaCurrencyConversionPredicateBuilder createdAt(LocalDate createdAt) {
        if (createdAt != null) {
            predicateList.add(criteriaBuilder.equal(currencyConversionEntityRoot.get(CREATED_AT), createdAt));
        }
        return this;
    }

    public JpaCurrencyConversionPredicateBuilder id(Long id) {
        if (id != null) {
            predicateList.add(criteriaBuilder.equal(currencyConversionEntityRoot.get("id"), id));
        }
        return this;
    }

    public Predicate[] buildPredicateArray() {
        return predicateList.toArray(new Predicate[0]);
    }
}
