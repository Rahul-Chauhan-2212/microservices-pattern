package org.pattern.commandservice.config;

import org.pattern.commandservice.auditor.AuditorAwareImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class AuditingConfig {

    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }
}
