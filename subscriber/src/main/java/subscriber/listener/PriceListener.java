package subscriber.listener;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import subscriber.service.impl.CsvPriceListenerService;

@Slf4j
@Component
@AllArgsConstructor
public class PriceListener {

    private CsvPriceListenerService csvPriceListenerService;

    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.group}")
    public void consumeCsvMessage(@Payload String message) {
        log.info("Consumed a new message -> {}", message);
        csvPriceListenerService.onMessage(message);
    }
}
