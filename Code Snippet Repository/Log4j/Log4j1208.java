    public StatusLoggerAdmin(final String contextName, final Executor executor) {
        super(executor, createNotificationInfo());
        this.contextName = contextName;
        try {
            final String mbeanName = String.format(PATTERN, Server.escape(contextName));
            objectName = new ObjectName(mbeanName);
        } catch (final Exception e) {
            throw new IllegalStateException(e);
        }
        removeListeners(contextName);
        StatusLogger.getLogger().registerListener(this);
    }
