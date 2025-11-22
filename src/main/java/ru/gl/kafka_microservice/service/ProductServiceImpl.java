package ru.gl.kafka_microservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ru.gl.kafka_microservice.service.dto.CreateProductDto;
import ru.gl.kafka_microservice.service.event.ProductCreatedEvent;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class ProductServiceImpl implements ProductService {

    private KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public ProductServiceImpl(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String createProduct(CreateProductDto createProductDto) throws ExecutionException, InterruptedException {
        //TODO save to db
        String productId = UUID.randomUUID().toString();

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(createProductDto.getTitle(),
                createProductDto.getPrice(),createProductDto.getQuantity(),productId);

        SendResult<String, ProductCreatedEvent> result = kafkaTemplate.send(
                "product_created_events_topic"
                ,productId,
                productCreatedEvent).get();

        LOGGER.info("Topic {}", result.getRecordMetadata().topic());
        LOGGER.info("Partition {}", result.getRecordMetadata().partition());
        LOGGER.info("Offset {}", result.getRecordMetadata().offset());

//        future.whenComplete((result, exception) -> {
//            if (exception != null) {
//                LOGGER.error("Failed to send message: {}", exception.getMessage());
//            } else {
//                LOGGER.info("Message sent successfully: {}", result.getRecordMetadata());
//            }
//        });

        //future.join(); //sync

        LOGGER.info("Return {}", productId);
        return productId;
    }
}
