    @Setup
    public void setUp() {
        System.setProperty("log4j.configurationFile", "log4j2-markerFilter-perf.xml");
        System.setProperty("logback.configurationFile", "logback-markerFilter-perf.xml");
        LOGBACK_FLOW_MARKER = MarkerFactory.getMarker("FLOW");
        LOGBACK_ENTRY_MARKER = MarkerFactory.getMarker("ENTRY");
        LOG4J_FLOW_MARKER = MarkerManager.getMarker("FLOW");
        LOG4J_ENTRY_MARKER = MarkerManager.getMarker("ENTRY");
        LOGBACK_ENTRY_MARKER.add(LOGBACK_FLOW_MARKER);
        LOG4J_ENTRY_MARKER.addParents(LOG4J_FLOW_MARKER);
        log4jLogger = LogManager.getLogger(MarkerFilterBenchmark.class);
        slf4jLogger = LoggerFactory.getLogger(MarkerFilterBenchmark.class);
    }
