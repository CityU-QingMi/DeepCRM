    private void testPerformance(final Map<String, String> contextData) throws Exception {
        putContextData(contextData);
        Target.LOGBACK.timedLoop(logger, logbacklogger, WARMUP);
        Target.LOG4J2.timedLoop(logger, logbacklogger, WARMUP);

        System.out.println("Single-threaded Log4j 2.0, "
                + (contextData.isEmpty() ? "EMPTY context" : "NON-EMPTY context"));

        final long result3 = Target.LOG4J2.timedLoop(logger, logbacklogger, COUNT);
        System.out.println("Single-threaded Logback, "
                + (contextData.isEmpty() ? "EMPTY context" : "NON-EMPTY context"));

        final long result2 = Target.LOGBACK.timedLoop(logger, logbacklogger, COUNT);

        System.out.println("###############################################");
        System.out.println("Logback: " + result2);
        System.out.println("Log4j 2.0: " + result3);
        System.out.println("###############################################");
    }
