    public LoggerContextAdmin(final LoggerContext loggerContext, final Executor executor) {
        super(executor, createNotificationInfo());
        this.loggerContext = Objects.requireNonNull(loggerContext, "loggerContext");
        try {
            final String ctxName = Server.escape(loggerContext.getName());
            final String name = String.format(PATTERN, ctxName);
            objectName = new ObjectName(name);
        } catch (final Exception e) {
            throw new IllegalStateException(e);
        }
        loggerContext.addPropertyChangeListener(this);
    }
