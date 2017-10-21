    @Setup
    public void setUp() throws Exception {
        System.setProperty("log4j.configurationFile", "log4j2-perfloc.xml");
        System.setProperty("log4j.configuration", "log4j12-perfloc.xml");
        System.setProperty("logback.configurationFile", "logback-perfloc.xml");

        deleteLogFiles();

        log4j2Logger = LogManager.getLogger(FileAppenderWithLocationBenchmark.class);
        log4j2RandomLogger = LogManager.getLogger("TestRandom");
        slf4jLogger = LoggerFactory.getLogger(FileAppenderWithLocationBenchmark.class);
        log4j1Logger = org.apache.log4j.Logger.getLogger(FileAppenderWithLocationBenchmark.class);
    }
