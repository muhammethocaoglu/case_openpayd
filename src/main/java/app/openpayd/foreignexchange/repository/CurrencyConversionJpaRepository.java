package app.openpayd.foreignexchange.repository;

import app.openpayd.foreignexchange.repository.entity.CurrencyConversionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyConversionJpaRepository extends JpaRepository<CurrencyConversionEntity, Long> {
}
