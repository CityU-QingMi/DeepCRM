    @Test
    public void testCustomConfigurationFactory() throws Exception {
        try {
            System.setProperty(ConfigurationFactory.CONFIGURATION_FACTORY_PROPERTY,
                    "org.apache.logging.log4j.core.config.builder.CustomConfigurationFactory");
            System.setProperty(Constants.LOG4J_CONTEXT_SELECTOR,
                    "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
            final Configuration config = ((LoggerContext) LogManager.getContext(false)).getConfiguration();
            validate(config);
        } finally {
            System.getProperties().remove(Constants.LOG4J_CONTEXT_SELECTOR);
            System.getProperties().remove(ConfigurationFactory.CONFIGURATION_FACTORY_PROPERTY);
        }
    }
