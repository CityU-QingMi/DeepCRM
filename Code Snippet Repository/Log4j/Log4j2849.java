    @Setup
    public void setUp() {
        System.setProperty("log4j.configurationFile", "log4j2-threadContextFilter-perf.xml");
        System.setProperty("logback.configurationFile", "logback-mdcFilter-perf.xml");

        log4jLogger = LogManager.getLogger(MDCFilterBenchmark.class);
        slf4jLogger = LoggerFactory.getLogger(MDCFilterBenchmark.class);

        staticSize = size;
    }
