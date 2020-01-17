package app.openpayd.foreignexchange.service;

import app.openpayd.foreignexchange.repository.CurrencyConversionJpaRepository;
import app.openpayd.foreignexchange.repository.entity.CurrencyConversionEntity;
import app.openpayd.foreignexchange.service.model.CreateCurrencyConversion;
import app.openpayd.foreignexchange.service.model.CurrencyConversion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyConverterService {

    private final ExchangeRateService exchangeRateService;
    private final CurrencyConversionJpaRepository currencyConversionJpaRepository;

    public CurrencyConversion create(CreateCurrencyConversion createCurrencyConversion) {
        Double exchangeRate = exchangeRateService.retrieve(createCurrencyConversion.getSourceCurrency(),
                createCurrencyConversion.getTargetCurrency());

        CurrencyConversionEntity currencyConversionEntity = populateCurrencyConversionEntity(createCurrencyConversion,
                exchangeRate);

        currencyConversionJpaRepository.save(currencyConversionEntity);

        return currencyConversionEntity.toModel();
    }

    private CurrencyConversionEntity populateCurrencyConversionEntity(CreateCurrencyConversion createCurrencyConversion, Double exchangeRate) {
        CurrencyConversionEntity currencyConversionEntity = new CurrencyConversionEntity();
        currencyConversionEntity.setSourceCurrency(createCurrencyConversion.getSourceCurrency());
        currencyConversionEntity.setTargetCurrency(createCurrencyConversion.getTargetCurrency());
        currencyConversionEntity.setSourceAmount(createCurrencyConversion.getSourceAmount());
        currencyConversionEntity.setAmountInTargetCurrency(exchangeRate * createCurrencyConversion.getSourceAmount());
        return currencyConversionEntity;
    }
}
