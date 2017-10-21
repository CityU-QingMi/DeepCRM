    public static void main(final String[] args) {
        // System.out.println(System.getProperty("java.class.path"));
        final String config = args.length == 0 ? "target/test-classes/log4j2-272.xml" : args[0];
        try (final LoggerContext ctx = Configurator.initialize(ConsoleAppenderAnsiMessagesMain.class.getName(), config)) {
            LOG.fatal("Fatal message.");
            LOG.error("Error message.");
            LOG.warn("Warning message.");
            LOG.info("Information message.");
            LOG.debug("Debug message.");
            LOG.trace("Trace message.");
            try {
                throw new NullPointerException();
            } catch (final Exception e) {
                LOG.error("Error message.", e);
                LOG.catching(Level.ERROR, e);
            }
            LOG.warn("this is ok \n And all \n this have only\t\tblack colour \n and here is colour again?");
            LOG.info("Information message.");
        }
    }
