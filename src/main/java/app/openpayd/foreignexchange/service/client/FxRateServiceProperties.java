package app.openpayd.foreignexchange.service.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ConfigurationProperties(prefix = "fxrateservice")
public class FxRateServiceProperties {

    @NotBlank
    private String baseUrl;

    @NotBlank
    private String latestRatesPath;

    @NotBlank
    private String apiKey;
}
