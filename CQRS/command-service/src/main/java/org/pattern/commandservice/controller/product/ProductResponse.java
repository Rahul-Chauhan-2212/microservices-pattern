package org.pattern.commandservice.controller.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pattern.commandservice.controller.BaseResponse;
import org.pattern.commandservice.domain.common.Extension;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductResponse extends BaseResponse {

    private UUID productId;

    private String productCode;

    private String productName;

    private List<Extension> extensions;
}
