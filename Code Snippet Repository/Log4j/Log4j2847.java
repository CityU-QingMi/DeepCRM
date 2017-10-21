    public void log4WithCounterAndFlag(final LogEvent event) {
        LoggerConfigBenchmark local = loggerConfig;
        while (!local.beforeLogEventCheckCounterPositive()) {
            local = loggerConfig;
        }
        try {
            local.log3(event);
        } finally {
            local.afterLogEvent2();
        }
    }
