package app.openpayd.foreignexchange.service.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class FxRateServiceClient {

    private final RestTemplate restTemplate;
    private final FxRateServiceProperties fxRateServiceProperties;

    public Double retrieveExchangeRate(String sourceCurrency, String targetCurrency) {
        String retrieveExchangeRateUrl = fxRateServiceProperties.getBaseUrl() + fxRateServiceProperties.getLatestRatesPath()
                + "?access_key=" + fxRateServiceProperties.getApiKey() + "&base=" + sourceCurrency + "&symbols=" +
                targetCurrency;

        ResponseEntity<FxRateServiceLatestRates> fxRateServiceLatestRatesResponseEntity = restTemplate
                .getForEntity(retrieveExchangeRateUrl, FxRateServiceLatestRates.class);

        return fxRateServiceLatestRatesResponseEntity.getBody().getRates().get(targetCurrency);
    }
}
