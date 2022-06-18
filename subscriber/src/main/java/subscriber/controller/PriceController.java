package subscriber.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import subscriber.model.Price;
import subscriber.service.impl.PriceService;

import java.util.List;

/**
 * The component is used only for test purposes
 */
@RestController
@AllArgsConstructor
@RequestMapping("/prices")
public class PriceController {

    private PriceService priceService;

    @GetMapping
    public ResponseEntity<List<Price>> getLatestPrices() {
        return ResponseEntity.ok(priceService.getLatest());
    }
}
