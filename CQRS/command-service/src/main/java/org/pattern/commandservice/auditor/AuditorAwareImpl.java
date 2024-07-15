package org.pattern.commandservice.auditor;

import org.pattern.commandservice.context.RequesterThreadLocalContext;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(RequesterThreadLocalContext.getRequesterName());
    }
}
