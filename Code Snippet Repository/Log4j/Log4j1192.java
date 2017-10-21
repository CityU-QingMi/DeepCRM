    public AsyncAppenderAdmin(final String contextName, final AsyncAppender appender) {
        // super(executor); // no notifications for now
        this.contextName = Objects.requireNonNull(contextName, "contextName");
        this.asyncAppender = Objects.requireNonNull(appender, "async appender");
        try {
            final String ctxName = Server.escape(this.contextName);
            final String configName = Server.escape(appender.getName());
            final String name = String.format(PATTERN, ctxName, configName);
            objectName = new ObjectName(name);
        } catch (final Exception e) {
            throw new IllegalStateException(e);
        }
    }
