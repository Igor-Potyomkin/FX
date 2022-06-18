package producer.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import producer.service.ProducerService;

/**
 * The component is used only for tests purposes.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/publish")
public class PublishController {

    private ProducerService producerService;

    @PostMapping
    public void publishMessage(@RequestBody String message) {
        producerService.publish(message);
    }
}
