package producer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Component for test purposes
 */
@Slf4j
@Service
public class ProducerServiceImpl implements ProducerService {

    private final String topic;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public ProducerServiceImpl(@Value("${kafka.topic}") String topic,
                               KafkaTemplate<String, String> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(String message) {
        log.info("Produce the message into topic: {}", topic);
        this.kafkaTemplate.send(topic, message);
    }
}
