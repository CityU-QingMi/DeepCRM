    public void test(final String[] args) {
        // System.out.println(System.getProperty("java.class.path"));
        final String config = args == null || args.length == 0 ? "target/test-classes/log4j2-console-style-ansi.xml"
                : args[0];
        try (final LoggerContext ctx = Configurator.initialize(ConsoleAppenderAnsiMessagesMain.class.getName(), config)) {
            final Logger logger = LogManager.getLogger(ConsoleAppenderAnsiStyleLayoutMain.class);
            logger.fatal("Fatal message.");
            logger.error("Error message.");
            logger.warn("Warning message.");
            logger.info("Information message.");
            logger.debug("Debug message.");
            logger.trace("Trace message.");
            logger.error("Error message.", new IOException("test"));
        }
    }
