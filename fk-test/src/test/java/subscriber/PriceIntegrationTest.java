package subscriber;

import model.Price;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Arrays;

@ActiveProfiles("test")
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = PriceIntegrationTest.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldGetLatestPriseTest() {
        final String PUBLISH_URL = "http://localhost:8080/publish";
        final String PRICES_URL = "http://localhost:8081/prices";

        final String payload = """
                106, EUR/USD, 1.1000, 1.2000, 01-06-2020 12:01:01.001
                ...
                107, EUR/JPY, 119.60, 119.90, 01-06-2020 12:01:02.002
                ...
                108, GBR/USD, 1.2500, 1.2560, 01-06-2020 12:01:02.002
                ...
                109, GBR/USD, 1.2499, 1.2561, 01-06-2020 12:01:02.100
                ...
                110, EUR/JPY, 119.61, 119.91, 01-06-2020 12:01:02.110""";


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);

        /* Publish */
        HttpEntity<String> request = new HttpEntity<>(payload, headers);
        ResponseEntity<String> publishResponse = restTemplate.postForEntity(PUBLISH_URL, request, String.class);

        Assertions.assertEquals(HttpStatus.OK, publishResponse.getStatusCode());

        /* Get prices */
        ResponseEntity<Price[]> pricesResponse = restTemplate.getForEntity(PRICES_URL, Price[].class);
        Assertions.assertEquals(HttpStatus.OK, pricesResponse.getStatusCode());

        Price[] prices = pricesResponse.getBody();
        Assertions.assertNotNull(prices);
        Assertions.assertEquals(5, prices.length);

        printToConsole(prices);
    }

    private void printToConsole(Price[] prices) {
        Arrays.stream(prices).forEach(p -> System.out.println(p.toString()));
    }
}
