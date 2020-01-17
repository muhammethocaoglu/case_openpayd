package app.openpayd.foreignexchange.rest.controller.dto;

import app.openpayd.foreignexchange.service.model.CreateCurrencyConversion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConvertCurrenciesRequest {

    @PositiveOrZero
    @NotNull
    private Double sourceAmount;

    @Builder.Default
    private String sourceCurrency = "EUR";

    @NotBlank
    private String targetCurrency;

    public CreateCurrencyConversion toModel() {
        return CreateCurrencyConversion.builder()
                .sourceAmount(sourceAmount)
                .sourceCurrency(sourceCurrency)
                .targetCurrency(targetCurrency)
                .build();
    }
}
