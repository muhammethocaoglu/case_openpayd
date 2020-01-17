package app.openpayd.foreignexchange.integration;

import app.openpayd.foreignexchange.rest.controller.dto.ListCurrencyConversionsResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@IT
@Sql(scripts = "classpath:data-currency-conversion-search.sql", executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:cleanup.sql", executionPhase = AFTER_TEST_METHOD)
public class ConversionListControllerIT extends AbstractIT {

    @Test
    void should_list_currency_conversions_by_createdAt() {

        //given
        String url = "http://localhost:" + serverPort + "/api/v1/conversions?page=0&size=5&createdAt=2019-06-05";

        //when
        ResponseEntity<ListCurrencyConversionsResponse> responseEntity = testRestTemplate.getForEntity(url,
                ListCurrencyConversionsResponse.class);

        //then
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();

        ListCurrencyConversionsResponse listCurrencyConversionsResponse = responseEntity.getBody();
        assertThat(listCurrencyConversionsResponse.getTotalSize()).isEqualTo(5);
        assertThat(listCurrencyConversionsResponse.getConvertCurrenciesList()).hasSize(5)
                .extracting("transactionId", "createdAt", "sourceCurrency", "targetCurrency",
                        "sourceAmount", "amountInTargetCurrency")
                .contains(tuple(10L, LocalDate.of(2019, 6, 5), "EUR", "USD", 40.0, 51.5),
                        tuple(11L, LocalDate.of(2019, 6, 5), "EUR", "USD", 41.0, 53.2),
                        tuple(12L, LocalDate.of(2019, 6, 5), "EUR", "USD", 42.0, 55.4),
                        tuple(13L, LocalDate.of(2019, 6, 5), "EUR", "USD", 45.0, 60.5),
                        tuple(14L, LocalDate.of(2019, 6, 5), "EUR", "USD", 50.0, 70.3));
    }
}
