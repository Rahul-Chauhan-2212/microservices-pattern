package org.pattern.commandservice.config;

import jakarta.validation.constraints.NotNull;
import org.axonframework.common.caching.Cache;
import org.axonframework.common.caching.WeakReferenceCache;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.config.Configurer;
import org.axonframework.config.ConfigurerModule;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.extensions.mongo.MongoTemplate;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.StorageStrategy;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.documentperevent.DocumentPerEventStorageStrategy;
import org.axonframework.lifecycle.Phase;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.interceptors.LoggingInterceptor;
import org.axonframework.serialization.Serializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonMongoConfig {

    @Bean
    public LoggingInterceptor<Message<?>> loggingInterceptor() {
        return new LoggingInterceptor<>();
    }

    @Bean
    public ConfigurerModule loggingInterceptorConfigurerModule(LoggingInterceptor<Message<?>> loggingInterceptor) {
        return new LoggingInterceptorConfigurerModule(loggingInterceptor);
    }

    @Bean
    public Cache giftCardCache() {
        return new WeakReferenceCache();
    }

    @Bean
    public EventStorageEngine eventStorageEngine(
            MongoTemplate mongoTemplate,
            TransactionManager transactionManager,
            Serializer snapshotSerializer,
            @Qualifier("eventSerializer") Serializer serializer,
            StorageStrategy storageStrategy,
            org.axonframework.config.Configuration configuration
    ) {
        return MongoEventStorageEngine.builder()
                .mongoTemplate(mongoTemplate)
                .transactionManager(transactionManager)
                .snapshotSerializer(snapshotSerializer)
                .eventSerializer(serializer)
                .storageStrategy(storageStrategy)
                .upcasterChain(configuration.upcasterChain())
                .build();
    }

    @Bean
    public StorageStrategy storageStrategy() {
        return new DocumentPerEventStorageStrategy();
    }

    /**
     * An example {@link ConfigurerModule} implementation to attach configuration to Axon's configuration life cycle.
     */
    private static class LoggingInterceptorConfigurerModule implements ConfigurerModule {

        private final LoggingInterceptor<Message<?>> loggingInterceptor;

        private LoggingInterceptorConfigurerModule(LoggingInterceptor<Message<?>> loggingInterceptor) {
            this.loggingInterceptor = loggingInterceptor;
        }

        @Override
        public void configureModule(@NotNull Configurer configurer) {
            configurer.eventProcessing(
                            processingConfigurer -> processingConfigurer.registerDefaultHandlerInterceptor(
                                    (config, processorName) -> loggingInterceptor
                            )
                    )
                    .onInitialize(this::registerInterceptorForBusses);
        }

        /**
         * Registers the {@link LoggingInterceptor} on the {@link org.axonframework.commandhandling.CommandBus},
         * {@link com.google.common.eventbus.EventBus}, {@link org.axonframework.queryhandling.QueryBus}, and
         * {@link org.axonframework.queryhandling.QueryUpdateEmitter}.
         * <p>
         * It does so right after the {@link Phase#INSTRUCTION_COMPONENTS}, to ensure all these infrastructure
         * components are constructed.
         *
         * @param config The {@link org.axonframework.config.Configuration} to retrieve the infrastructure components
         *               from.
         */
        @SuppressWarnings("resource") // We do not require to handle the returned Registration object.
        private void registerInterceptorForBusses(org.axonframework.config.Configuration config) {
            config.onStart(Phase.INSTRUCTION_COMPONENTS + 1, () -> {
                config.commandBus().registerHandlerInterceptor(loggingInterceptor);
                config.commandBus().registerDispatchInterceptor(loggingInterceptor);
                config.eventBus().registerDispatchInterceptor(loggingInterceptor);
                config.queryBus().registerHandlerInterceptor(loggingInterceptor);
                config.queryBus().registerDispatchInterceptor(loggingInterceptor);
                config.queryUpdateEmitter().registerDispatchInterceptor(loggingInterceptor);
            });
        }
    }

}