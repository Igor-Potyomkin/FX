package subscriber.client;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import subscriber.model.Price;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class PriceClient implements Client<Price> {

    @Override
    public void send(List<Price> prices) {
        /*
         * Just an example.
         * WebClient webClient = WebClient.create(priceProperties.getUrl());
         * Other implementation should be here.
         */
        log.info("The prices have been sent to the client. Prices size: {}", prices.size());
    }
}
