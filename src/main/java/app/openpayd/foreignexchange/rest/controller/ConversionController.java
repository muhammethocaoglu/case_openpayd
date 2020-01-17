package app.openpayd.foreignexchange.rest.controller;

import app.openpayd.foreignexchange.rest.controller.dto.ConvertCurrenciesRequest;
import app.openpayd.foreignexchange.rest.controller.dto.ConvertCurrenciesResponse;
import app.openpayd.foreignexchange.service.CurrencyConverterService;
import app.openpayd.foreignexchange.service.model.CurrencyConversion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/conversions")
public class ConversionController {

    private final CurrencyConverterService currencyConverterService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConvertCurrenciesResponse create(@Valid @RequestBody ConvertCurrenciesRequest convertCurrenciesRequest) {
        CurrencyConversion currencyConversion = currencyConverterService.create(convertCurrenciesRequest.toModel());
        return ConvertCurrenciesResponse.of(currencyConversion);
    }
}
