package org.pattern.commandservice.aggregate;


import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.pattern.commandservice.command.product.CreateProductCommand;
import org.pattern.commandservice.domain.common.Extension;
import org.pattern.commandservice.event.product.ProductCreatedEvent;

import java.util.List;

@Slf4j
@Aggregate
public class ProductAggregate extends BaseAggregate {

    private String productCode;

    private String productName;

    private List<Extension> extensions;

    public ProductAggregate() {

    }


    @CommandHandler
    public ProductAggregate(CreateProductCommand command) {
    create(new ProductCreatedEvent(command.getId(), command.getProductCode(), command.getProductName(), command.getExtensions()));
    }

    public void create(ProductCreatedEvent productCreatedEvent) {
        AggregateLifecycle.apply(productCreatedEvent);
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        this.id = productCreatedEvent.getId();
        this.productCode = productCreatedEvent.getProductCode();
        this.productName = productCreatedEvent.getProductName();
        this.extensions = productCreatedEvent.getExtensions();
        log.info("Created product with code {} and name {}", productCode, productName);
    }
}
