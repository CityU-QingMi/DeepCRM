    public static void main(final String[] args) {
        try (final LoggerContext ctx = Configurator.initialize(ConsoleAppenderAnsiMessagesMain.class.getName(),
                "target/test-classes/log4j2-console-highlight-default.xml")) {
            LOG.fatal("Fatal message.");
            LOG.error("Error message.");
            LOG.warn("Warning message.");
            LOG.info("Information message.");
            LOG.debug("Debug message.");
            LOG.trace("Trace message.");
            LOG.error("Error message.", new IOException("test"));
        }
    }
