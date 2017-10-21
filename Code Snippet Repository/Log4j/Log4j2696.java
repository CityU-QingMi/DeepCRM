    @Before
    public void setUp() throws Exception {

        final File file = new File("target/persistent");
        deleteFiles(file);

/**/
/**/
/**/
/**/
        final int primaryPort = AvailablePortFinder.getNextAvailable();
        final int altPort = AvailablePortFinder.getNextAvailable();
        System.setProperty("primaryPort", Integer.toString(primaryPort));
        System.setProperty("alternatePort", Integer.toString(altPort));
        primary = new EventCollector(primaryPort);
        alternate = new EventCollector(altPort);
        System.setProperty(ConfigurationFactory.CONFIGURATION_FILE_PROPERTY, CONFIG);
        ctx = LoggerContext.getContext(false);
        ctx.reconfigure();
    }
