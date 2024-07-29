package org.pattern.commandservice.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class BaseResponse<T> {

    private T id;

    private LocalDateTime createdAt;

    private String createBy;

    private LocalDateTime updatedAt;

    private String updateBy;

}
