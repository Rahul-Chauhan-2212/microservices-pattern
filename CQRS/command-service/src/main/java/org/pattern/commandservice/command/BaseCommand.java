package org.pattern.commandservice.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;


@Data
public abstract class BaseCommand<T> {

    @TargetAggregateIdentifier
    private final T id;

}
