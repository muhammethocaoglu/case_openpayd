package app.openpayd.foreignexchange.rest.controller;

import app.openpayd.foreignexchange.rest.controller.dto.RetrieveExchangeRateResponse;
import app.openpayd.foreignexchange.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exchange-rates")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public RetrieveExchangeRateResponse retrieve(@RequestParam(defaultValue = "EUR") String sourceCurrency,
                                                 @RequestParam String targetCurrency) {
        Double exchangeRate = exchangeRateService.retrieve(sourceCurrency, targetCurrency);
        return RetrieveExchangeRateResponse.builder().exchangeRate(exchangeRate).build();
    }
}
