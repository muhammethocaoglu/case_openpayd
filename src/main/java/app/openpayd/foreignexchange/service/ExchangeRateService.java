package app.openpayd.foreignexchange.service;

import app.openpayd.foreignexchange.service.client.FxRateServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeRateService {

    private final FxRateServiceClient fxRateServiceClient;

    public Double retrieve(String baseCurrency, String targetCurrency) {
        return fxRateServiceClient.retrieveExchangeRate(baseCurrency, targetCurrency);
    }
}
