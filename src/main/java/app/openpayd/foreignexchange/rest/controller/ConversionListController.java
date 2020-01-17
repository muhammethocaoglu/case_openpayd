package app.openpayd.foreignexchange.rest.controller;

import app.openpayd.foreignexchange.rest.controller.dto.CurrencyConversionListRequest;
import app.openpayd.foreignexchange.rest.controller.dto.ListCurrencyConversionsResponse;
import app.openpayd.foreignexchange.rest.controller.dto.RetrieveExchangeRateResponse;
import app.openpayd.foreignexchange.service.CurrencyConversionSearchService;
import app.openpayd.foreignexchange.service.model.SearchCurrencyConversionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/conversions")
public class ConversionListController {

    private final CurrencyConversionSearchService currencyConversionSearchService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ListCurrencyConversionsResponse list(CurrencyConversionListRequest currencyConversionListRequest) {
        SearchCurrencyConversionResult searchCurrencyConversionResult = currencyConversionSearchService
                .search(currencyConversionListRequest.toModel());
        return ListCurrencyConversionsResponse.of(searchCurrencyConversionResult);


    }
}
