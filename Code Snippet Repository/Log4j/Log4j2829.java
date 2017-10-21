    @Setup
    public void setUp() throws Exception {
        System.setProperty("log4j.configurationFile", "log4j2-perf.xml");
        System.setProperty("log4j.configuration", "log4j12-perf.xml");
        System.setProperty("logback.configurationFile", "logback-perf.xml");

        deleteLogFiles();

        log4j2Logger = LogManager.getLogger(FileAppenderBenchmark.class);
        log4j2AsyncAppender = LogManager.getLogger("AsyncAppender");
        log4j2AsyncDisruptor = LogManager.getLogger("AsyncDisruptorAppender");
        log4j2AsyncLogger = LogManager.getLogger("AsyncLogger");
        log4j2MemoryLogger = LogManager.getLogger("MemoryMapped");
        log4j2RandomLogger = LogManager.getLogger("TestRandom");
        slf4jLogger = LoggerFactory.getLogger(FileAppenderBenchmark.class);
        slf4jAsyncLogger = LoggerFactory.getLogger("Async");
        log4j1Logger = org.apache.log4j.Logger.getLogger(FileAppenderBenchmark.class);

        julFileHandler = new FileHandler("target/testJulLog.log");
        julLogger = java.util.logging.Logger.getLogger(getClass().getName());
        julLogger.setUseParentHandlers(false);
        julLogger.addHandler(julFileHandler);
        julLogger.setLevel(Level.ALL);
    }
