package ru.gl.kafka_microservice.service.event;

import java.math.BigDecimal;

public class ProductCreatedEvent {
    private String title;
    private BigDecimal price;
    private Integer quantity;
    private String product_id;

    public ProductCreatedEvent() {
    }

    public ProductCreatedEvent(String title, BigDecimal price, Integer quantity, String product_id) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.product_id = product_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}
