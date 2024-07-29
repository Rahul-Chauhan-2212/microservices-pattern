package org.pattern.commandservice.command.product;

import lombok.Getter;
import lombok.Setter;
import org.pattern.commandservice.command.BaseCommand;
import org.pattern.commandservice.domain.common.Extension;

import java.util.List;

@Getter
@Setter
public class CreateProductCommand extends BaseCommand<String> {

    private String productCode;

    private String productName;

    private List<Extension> extensions;

    public CreateProductCommand(String id, String productCode, String productName, List<Extension> extensions) {
        super(id);
        this.productCode = productCode;
        this.productName = productName;
        this.extensions = extensions;
    }
}
