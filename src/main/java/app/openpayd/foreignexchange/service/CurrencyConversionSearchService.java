package app.openpayd.foreignexchange.service;

import app.openpayd.foreignexchange.repository.entity.CurrencyConversionEntity;
import app.openpayd.foreignexchange.service.model.CurrencyConversion;
import app.openpayd.foreignexchange.service.model.SearchCurrencyConversion;
import app.openpayd.foreignexchange.service.model.SearchCurrencyConversionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrencyConversionSearchService {

    private final EntityManager entityManager;

    public SearchCurrencyConversionResult search(SearchCurrencyConversion searchCurrencyConversion) {
        return SearchCurrencyConversionResult.builder()
                .page(searchCurrencyConversion.getPage())
                .size(searchCurrencyConversion.getSize())
                .totalSize(count(searchCurrencyConversion))
                .currencyConversionList(retrieveSearchCurrencyConversionList(searchCurrencyConversion))
                .build();
    }

    private Long count(SearchCurrencyConversion searchCurrencyConversion) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> queryForCount = criteriaBuilder.createQuery(Long.class);
        Root<CurrencyConversionEntity> rootForCount = queryForCount.from(CurrencyConversionEntity.class);
        Predicate[] predicateArrayForCountList = generatePredicateArray(searchCurrencyConversion, criteriaBuilder, rootForCount);

        queryForCount.select(criteriaBuilder.countDistinct(rootForCount.get("id"))).where(predicateArrayForCountList).distinct(true);
        return entityManager.createQuery(queryForCount).getSingleResult();
    }

    private List<CurrencyConversion> retrieveSearchCurrencyConversionList(SearchCurrencyConversion searchCurrencyConversion) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<CurrencyConversionEntity> query = criteriaBuilder.createQuery(CurrencyConversionEntity.class);
        Root<CurrencyConversionEntity> currencyConversionEntityRoot = query.from(CurrencyConversionEntity.class);

        Predicate[] predicateArrayForRetrieveList = generatePredicateArray(searchCurrencyConversion, criteriaBuilder,
                currencyConversionEntityRoot);

        query.orderBy(criteriaBuilder.desc(currencyConversionEntityRoot.get("createdAt")));
        query.select(currencyConversionEntityRoot).distinct(true);
        query.where(predicateArrayForRetrieveList);

        return entityManager.createQuery(query)
                .setFirstResult(searchCurrencyConversion.getPage() * searchCurrencyConversion.getSize())
                .setMaxResults(searchCurrencyConversion.getSize())
                .getResultList()
                .stream()
                .map(CurrencyConversionEntity::toModel)
                .collect(Collectors.toList());
    }

    private Predicate[] generatePredicateArray(SearchCurrencyConversion searchCurrencyConversion, CriteriaBuilder criteriaBuilder,
                                               Root<CurrencyConversionEntity> currencyConversionEntityRoot) {
        return new JpaCurrencyConversionPredicateBuilder(criteriaBuilder, currencyConversionEntityRoot)
                .createdAt(searchCurrencyConversion.getCreatedAt())
                .id(searchCurrencyConversion.getId())
                .buildPredicateArray();
    }

}
