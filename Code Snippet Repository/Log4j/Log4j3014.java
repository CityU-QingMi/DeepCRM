    @Override
    public Statement apply(final Statement base, final Description description) {
        testClassName = description.getClassName();
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                context = (LoggerContext) LoggerFactory.getILoggerFactory();
                final GenericConfigurator configurator = new JoranConfigurator();
                configurator.setContext(context);
                configurator.doConfigure(configLocation);
                base.evaluate();
            }
        };
    }
