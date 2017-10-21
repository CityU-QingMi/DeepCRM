    @Test
    public void testBuildConfiguration() throws Exception {
        try {
            System.setProperty(Constants.LOG4J_CONTEXT_SELECTOR,
                    "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
            final ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();
            CustomConfigurationFactory.addTestFixtures("config name", builder);
            final Configuration configuration = builder.build();
            try (LoggerContext ctx = Configurator.initialize(configuration)) {
                validate(configuration);
            }
        } finally {
            System.getProperties().remove(Constants.LOG4J_CONTEXT_SELECTOR);
        }
    }
