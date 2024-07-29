package org.pattern.commandservice.event;

import lombok.Data;

@Data
public abstract class BaseEvent<T> {

    private final T id;
}
