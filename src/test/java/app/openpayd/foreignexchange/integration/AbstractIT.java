package app.openpayd.foreignexchange.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

public class AbstractIT {

    @LocalServerPort
    protected int serverPort;

    @Autowired
    protected TestRestTemplate testRestTemplate;
}
