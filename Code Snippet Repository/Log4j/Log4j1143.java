    public LoggerContext getContext(final String fqcn, final ClassLoader loader, final Object externalContext,
            final boolean currentContext, final List<URI> configLocations, final String name) {
        final LoggerContext ctx = selector
                .getContext(fqcn, loader, currentContext, null/*this probably needs to change*/);
        if (externalContext != null && ctx.getExternalContext() == null) {
            ctx.setExternalContext(externalContext);
        }
        if (name != null) {
            ctx.setName(name);
        }
        if (ctx.getState() == LifeCycle.State.INITIALIZED) {
            if ((configLocations != null && !configLocations.isEmpty())) {
                ContextAnchor.THREAD_CONTEXT.set(ctx);
                final List<AbstractConfiguration> configurations = new ArrayList<>(configLocations.size());
                for (final URI configLocation : configLocations) {
                    final Configuration currentReadConfiguration = ConfigurationFactory.getInstance()
                            .getConfiguration(ctx, name, configLocation);
                    if (currentReadConfiguration instanceof AbstractConfiguration) {
                        configurations.add((AbstractConfiguration) currentReadConfiguration);
                    } else {
                        LOGGER.error(
                                "Found configuration {}, which is not an AbstractConfiguration and can't be handled by CompositeConfiguration",
                                configLocation);
                    }
                }
                final CompositeConfiguration compositeConfiguration = new CompositeConfiguration(configurations);
                LOGGER.debug("Starting LoggerContext[name={}] from configurations at {}", ctx.getName(),
                        configLocations);
                ctx.start(compositeConfiguration);
                ContextAnchor.THREAD_CONTEXT.remove();
            } else {
                ctx.start();
            }
        }
        return ctx;
    }
