    @Test
    public void testClosingLoggerContext() {
        // initial config with indexed rollover
        try (LoggerContext loggerContext1 = Configurator.initialize(buildConfigA().build())) {
            validateAppender(loggerContext1, "target/rolling-update-date/foo.log.%i");
        }

        // rebuild config with date based rollover
        try (LoggerContext loggerContext2 = Configurator.initialize(buildConfigB().build())) {
            validateAppender(loggerContext2, "target/rolling-update-date/foo.log.%d{yyyy-MM-dd-HH:mm:ss}.%i");
        }
    }
