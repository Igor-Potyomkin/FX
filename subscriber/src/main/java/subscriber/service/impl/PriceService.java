package subscriber.service.impl;

import org.springframework.stereotype.Service;
import subscriber.model.Price;

import java.util.ArrayList;
import java.util.List;

/**
 * The test component is used to store parsed prices and then fetch.
 * Decided not to add additional component "repository" and keep storage here.
 */
@Service
public class PriceService {

    public static List<Price> PRICES_STORAGE = new ArrayList<>();

    public void store(List<Price> prices) {
        if (!PRICES_STORAGE.isEmpty()) {
            PRICES_STORAGE.clear();
        }

        PRICES_STORAGE.addAll(prices);
    }

    public List<Price> getLatest() {
        return PRICES_STORAGE;
    }
}
