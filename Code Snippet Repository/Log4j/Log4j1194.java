    public LoggerConfigAdmin(final LoggerContext loggerContext, final LoggerConfig loggerConfig) {
        // super(executor); // no notifications for now
        this.loggerContext = Objects.requireNonNull(loggerContext, "loggerContext");
        this.loggerConfig = Objects.requireNonNull(loggerConfig, "loggerConfig");
        try {
            final String ctxName = Server.escape(loggerContext.getName());
            final String configName = Server.escape(loggerConfig.getName());
            final String name = String.format(PATTERN, ctxName, configName);
            objectName = new ObjectName(name);
        } catch (final Exception e) {
            throw new IllegalStateException(e);
        }
    }
