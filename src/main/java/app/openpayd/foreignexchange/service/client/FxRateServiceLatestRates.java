package app.openpayd.foreignexchange.service.client;

import lombok.*;

import java.time.LocalDate;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FxRateServiceLatestRates {

    private Boolean success;
    private Long timestamp;
    private String base;
    private LocalDate date;
    private Map<String, Double> rates;
}
