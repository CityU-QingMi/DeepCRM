    @BeforeClass
    public static void beforeClass() {
        final File file = new File("target", "AsyncLoggerLocationTest.log");
        file.delete();
        
        System.setProperty(Constants.LOG4J_CONTEXT_SELECTOR,
                AsyncLoggerContextSelector.class.getName());
        System.setProperty(ConfigurationFactory.CONFIGURATION_FILE_PROPERTY,
                "AsyncLoggerLocationTest.xml");
    }
