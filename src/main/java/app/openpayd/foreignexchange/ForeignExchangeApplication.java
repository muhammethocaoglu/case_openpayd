package app.openpayd.foreignexchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ForeignExchangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForeignExchangeApplication.class, args);
    }
}
