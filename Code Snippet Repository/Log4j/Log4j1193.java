    public ContextSelectorAdmin(final String contextName, final ContextSelector selector) {
        super();
        this.selector = Objects.requireNonNull(selector, "ContextSelector");
        try {
            final String mbeanName = String.format(PATTERN, Server.escape(contextName));
            objectName = new ObjectName(mbeanName);
        } catch (final Exception e) {
            throw new IllegalStateException(e);
        }
    }
