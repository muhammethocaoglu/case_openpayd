package app.openpayd.foreignexchange.service.client;

import app.openpayd.foreignexchange.exception.ForeignExchangeBusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class FxRateServiceClient {

    private final RestTemplate restTemplate;
    private final FxRateServiceProperties fxRateServiceProperties;

    public Double retrieveExchangeRate(String sourceCurrency, String targetCurrency) {
        String retrieveExchangeRateUrl = fxRateServiceProperties.getBaseUrl() + fxRateServiceProperties.getLatestRatesPath()
                + "?access_key=" + fxRateServiceProperties.getApiKey() + "&base=" + sourceCurrency + "&symbols=" +
                targetCurrency;

        ResponseEntity<FxRateServiceLatestRates> fxRateServiceLatestRatesResponseEntity;
        try {
            fxRateServiceLatestRatesResponseEntity = restTemplate
                    .getForEntity(retrieveExchangeRateUrl, FxRateServiceLatestRates.class);
        } catch(Exception ex) {
            log.error("An error occurred while calling fx rate service", ex);
            throw new ForeignExchangeBusinessException("foreignexchange.api.fxrate.service.client.retrieve.exchange.rate.failed");
        }

        return fxRateServiceLatestRatesResponseEntity.getBody().getRates().get(targetCurrency);
    }
}
