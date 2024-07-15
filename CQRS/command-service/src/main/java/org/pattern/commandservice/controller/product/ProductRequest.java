package org.pattern.commandservice.controller.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pattern.commandservice.domain.common.Extension;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductRequest {

    @NotEmpty(message = "product.code.empty")
    @Size(min = 3, max = 6, message = "product.code.size.incorrect")
    private String productCode;

    private String productName;

    private List<Extension> extensions;

}
