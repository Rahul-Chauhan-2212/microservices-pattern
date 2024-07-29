package org.pattern.commandservice.event.product;

import lombok.Getter;
import lombok.Setter;
import org.pattern.commandservice.domain.common.Extension;
import org.pattern.commandservice.event.BaseEvent;

import java.util.List;

@Getter
@Setter
public class ProductCreatedEvent extends BaseEvent<String> {

    private String productCode;

    private String productName;

    private List<Extension> extensions;

    public ProductCreatedEvent(String id, String productCode, String productName, List<Extension> extensions) {
        super(id);
        this.productCode = productCode;
        this.productName = productName;
        this.extensions = extensions;
    }
}
