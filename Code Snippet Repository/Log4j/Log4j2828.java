    @Setup
    public void setUp() {
        System.setProperty("log4j.configurationFile", "log4j2-perf2.xml");
        System.setProperty("log4j.configuration", "log4j12-perf2.xml");
        System.setProperty("logback.configurationFile", "logback-perf2.xml");

        log4jLogger = LogManager.getLogger(DebugDisabledBenchmark.class);
        slf4jLogger = LoggerFactory.getLogger(DebugDisabledBenchmark.class);
        log4jClassicLogger = org.apache.log4j.Logger.getLogger(DebugDisabledBenchmark.class);
        j = Integer.valueOf(2);
    }
