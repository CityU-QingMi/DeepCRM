    @Setup(Level.Trial)
    public void up() {
        System.setProperty("log4j.configurationFile", "perf-WithoutAnyAppender-location.xml");
        System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        System.setProperty("AsyncLogger.RingBufferSize", "262144");
        System.setProperty("AsyncLogger.WaitStrategy", "Yield");
        //System.setProperty("log4j2.enable.threadlocals", "true");
        //System.setProperty("log4j.format.msg.async", "true");

        logger = LogManager.getLogger(getClass());
        new File("perftest.log").delete();
    }
