    public AppenderAdmin(final String contextName, final Appender appender) {
        // super(executor); // no notifications for now
        this.contextName = Objects.requireNonNull(contextName, "contextName");
        this.appender = Objects.requireNonNull(appender, "appender");
        try {
            final String ctxName = Server.escape(this.contextName);
            final String configName = Server.escape(appender.getName());
            final String name = String.format(PATTERN, ctxName, configName);
            objectName = new ObjectName(name);
        } catch (final Exception e) {
            throw new IllegalStateException(e);
        }
    }
