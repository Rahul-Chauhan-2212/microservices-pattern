package org.pattern.commandservice.aggregate;

import org.axonframework.modelling.command.AggregateIdentifier;

public class BaseAggregate {

    @AggregateIdentifier
    public String id;
}
