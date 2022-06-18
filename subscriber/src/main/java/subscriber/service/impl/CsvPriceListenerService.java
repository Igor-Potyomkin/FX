package subscriber.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import subscriber.client.PriceClient;
import subscriber.model.Price;
import subscriber.parser.MessageParser;
import subscriber.service.PriceListenerService;

import java.util.List;

@Service
@AllArgsConstructor
public class CsvPriceListenerService implements PriceListenerService {

    private PriceService priceService;

    private PriceClient priceClient;

    private MessageParser messageParser;

    @Override
    public void onMessage(String message) {
        List<Price> prices = messageParser.parse(message);

        if (!prices.isEmpty()) {
            prices.forEach(Price::processMargin);
            priceClient.send(prices);
            priceService.store(prices);
        }
    }
}
