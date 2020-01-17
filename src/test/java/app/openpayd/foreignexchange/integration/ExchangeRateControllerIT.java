package app.openpayd.foreignexchange.integration;

import app.openpayd.foreignexchange.rest.controller.dto.RetrieveExchangeRateResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@IT
public class ExchangeRateControllerIT extends AbstractIT {

    @Test
    void should_exchange_rates() {

        //given
        String url = "http://localhost:" + serverPort + "/api/v1/exchange-rates?baseCurrency=EUR&targetCurrency=USD";

        //when
        ResponseEntity<RetrieveExchangeRateResponse> responseEntity = testRestTemplate.getForEntity(url,
                RetrieveExchangeRateResponse.class);

        //then
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        RetrieveExchangeRateResponse retrieveExchangeRateResponse = responseEntity.getBody();
        assertThat(retrieveExchangeRateResponse)
                .isNotNull()
                .hasFieldOrProperty("exchangeRate")
                .hasNoNullFieldsOrProperties();
    }
}
