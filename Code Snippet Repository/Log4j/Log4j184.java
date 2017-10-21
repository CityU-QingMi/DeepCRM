    public Provider(final Integer priority, final String versions,
                    final Class<? extends LoggerContextFactory> loggerContextFactoryClass,
                    final Class<? extends ThreadContextMap> threadContextMapClass) {
        this.url = null;
        this.classLoader = null;
        this.priority = priority;
        this.loggerContextFactoryClass = loggerContextFactoryClass;
        this.threadContextMapClass = threadContextMapClass;
        this.className = null;
        this.threadContextMap = null;
        this.versions = versions;
    }
