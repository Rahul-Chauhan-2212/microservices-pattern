package org.pattern.commandservice.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private String errorCode;

    private String errorMessage;

    private Map<String, List<String>> fieldErrors;

    public ErrorResponse(String errorCode, String errorMessage, Map<String, List<String>> fieldErrors) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.fieldErrors = fieldErrors;
    }

    public ErrorResponse(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
