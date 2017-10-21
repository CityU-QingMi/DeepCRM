    @Override
    public Statement apply(final Statement base, final Description description) {
        // Hack: Using -DEBUG as a JVM param sets a property called "EBUG"...
        if (System.getProperties().containsKey("EBUG")) {
            StatusLogger.getLogger().setLevel(Level.DEBUG);
        }
        testClassName = description.getClassName();
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                if (contextSelectorClass != null) {
                    System.setProperty(Constants.LOG4J_CONTEXT_SELECTOR, contextSelectorClass.getName());
                }
                // TODO Consider instead of the above:
                // LogManager.setFactory(new Log4jContextFactory(LoaderUtil.newInstanceOf(contextSelectorClass)));
                System.setProperty(SYS_PROP_KEY_CLASS_NAME, description.getClassName());
                System.setProperty(SYS_PROP_KEY_DISPLAY_NAME, description.getDisplayName());
                loggerContext = Configurator.initialize(description.getDisplayName(),
                        description.getTestClass().getClassLoader(), configLocation);
                try {
                    base.evaluate();
                } finally {
                    if (!Configurator.shutdown(loggerContext, shutdownTimeout, shutdownTimeUnit)) {
                        StatusLogger.getLogger().error("Logger context {} did not shutdown completely after {} {}.",
                                loggerContext.getName(), shutdownTimeout, shutdownTimeUnit);
                    }
                    loggerContext = null;
                    contextSelectorClass = null;
                    StatusLogger.getLogger().reset();
                    System.clearProperty(Constants.LOG4J_CONTEXT_SELECTOR);
                    System.clearProperty(SYS_PROP_KEY_CLASS_NAME);
                    System.clearProperty(SYS_PROP_KEY_DISPLAY_NAME);
                }
            }
        };

    }
