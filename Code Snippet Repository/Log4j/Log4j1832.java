    private void testFactoryMethod() {
        final CronTriggeringPolicy triggerPolicy = createPolicy();
        final DefaultRolloverStrategy rolloverStrategy = createStrategy();

        try (RollingFileManager fileManager = RollingFileManager.getFileManager("target/testcmd3.log",
                "target/testcmd3.log.%d{yyyy-MM-dd}", true, true, triggerPolicy, rolloverStrategy, null,
                PatternLayout.createDefaultLayout(), 0, true, false, null, null, null, configuration)) {
            // trigger rollover
            fileManager.initialize();
            fileManager.rollover();
        }
    }
