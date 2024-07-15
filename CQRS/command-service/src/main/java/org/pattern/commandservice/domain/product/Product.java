package org.pattern.commandservice.domain.product;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pattern.commandservice.domain.common.BaseEntity;
import org.pattern.commandservice.domain.common.Extension;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Document(collection = "product", collation = "2.0")
public class Product extends BaseEntity {

    @Id
    @NotNull
    private UUID productId;

    @NotNull
    private String productCode;

    private String productName;

    private List<Extension> extensions;

}
