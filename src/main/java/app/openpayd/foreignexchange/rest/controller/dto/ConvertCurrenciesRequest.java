package app.openpayd.foreignexchange.rest.controller.dto;

import app.openpayd.foreignexchange.service.model.CreateCurrencyConversion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConvertCurrenciesRequest {

    private Double sourceAmount;

    @Builder.Default
    private String sourceCurrency = "EUR";

    private String targetCurrency;

    public CreateCurrencyConversion toModel() {
        return CreateCurrencyConversion.builder()
                .sourceAmount(sourceAmount)
                .sourceCurrency(sourceCurrency)
                .targetCurrency(targetCurrency)
                .build();
    }
}
