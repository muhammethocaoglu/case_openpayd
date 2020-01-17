package app.openpayd.foreignexchange.integration;

import app.openpayd.foreignexchange.rest.controller.dto.ConvertCurrenciesRequest;
import app.openpayd.foreignexchange.rest.controller.dto.ConvertCurrenciesResponse;
import app.openpayd.foreignexchange.rest.controller.dto.RetrieveExchangeRateResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

@IT
@Sql(scripts = "classpath:cleanup.sql", executionPhase = AFTER_TEST_METHOD)
public class ConversionControllerIT extends AbstractIT {

    @Test
    void should_convert_currencies() {

        //given
        String url = "http://localhost:" + serverPort + "/api/v1/conversions";
        ConvertCurrenciesRequest convertCurrenciesRequest = ConvertCurrenciesRequest.builder()
                .sourceAmount(50.0)
                .targetCurrency("USD")
                .build();

        //when
        ResponseEntity<ConvertCurrenciesResponse> responseEntity = testRestTemplate.postForEntity(url,
                convertCurrenciesRequest, ConvertCurrenciesResponse.class);

        //then
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isNotNull();
        ConvertCurrenciesResponse convertCurrenciesResponse = responseEntity.getBody();
        assertThat(convertCurrenciesResponse)
                .isNotNull()
                .hasFieldOrProperty("transactionId")
                .hasFieldOrProperty("amountInTargetCurrency")
                .hasNoNullFieldsOrProperties();
    }
}
