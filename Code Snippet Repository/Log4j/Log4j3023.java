    private void initializeJndi(final String location) {
        final URI configLocation = getConfigURI(location);

        if (this.name == null) {
            throw new IllegalStateException("A log4jContextName context parameter is required");
        }

        LoggerContext context;
        final LoggerContextFactory factory = LogManager.getFactory();
        if (factory instanceof Log4jContextFactory) {
            final ContextSelector selector = ((Log4jContextFactory) factory).getSelector();
            if (selector instanceof NamedContextSelector) {
                this.namedContextSelector = (NamedContextSelector) selector;
                context = this.namedContextSelector.locateContext(this.name, this.servletContext, configLocation);
                ContextAnchor.THREAD_CONTEXT.set(context);
                if (context.isInitialized()) {
                    context.start();
                }
                ContextAnchor.THREAD_CONTEXT.remove();
            } else {
                LOGGER.warn("Potential problem: Selector is not an instance of NamedContextSelector.");
                return;
            }
        } else {
            LOGGER.warn("Potential problem: LoggerContextFactory is not an instance of Log4jContextFactory.");
            return;
        }
        this.loggerContext = context;
        LOGGER.debug("Created logger context for [{}] using [{}].", this.name, context.getClass().getClassLoader());
    }
