package app.openpayd.foreignexchange.rest.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RetrieveExchangeRateResponse {

    private Double exchangeRate;
}
