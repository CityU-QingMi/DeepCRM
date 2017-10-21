    public static void main(final String[] args) {
        try (final LoggerContext ctx = Configurator.initialize(ConsoleAppenderAnsiMessagesMain.class.getName(),
                "target/test-classes/log4j2-console.xml")) {
            LOG.fatal("\u001b[1;35mFatal message.\u001b[0m");
            LOG.error("\u001b[1;31mError message.\u001b[0m");
            LOG.warn("\u001b[0;33mWarning message.\u001b[0m");
            LOG.info("\u001b[0;32mInformation message.\u001b[0m");
            LOG.debug("\u001b[0;36mDebug message.\u001b[0m");
            LOG.trace("\u001b[0;30mTrace message.\u001b[0m");
            LOG.error("\u001b[1;31mError message.\u001b[0m", new IOException("test"));
        }
    }
