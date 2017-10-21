    static void test(final String[] args, final String config) {
        // System.out.println(System.getProperty("java.class.path"));
        try (final LoggerContext ctx = Configurator.initialize(ConsoleAppenderNoAnsiStyleLayoutMain.class.getName(),
                config)) {
            LOG.fatal("Fatal message.");
            LOG.error("Error message.");
            LOG.warn("Warning message.");
            LOG.info("Information message.");
            LOG.debug("Debug message.");
            LOG.trace("Trace message.");
            logThrowableFromMethod();
            // This will log the stack trace as well:
            final IOException ioException = new IOException("test");
            LOG.error("Error message {}", "Hi", ioException);
            final Throwable t = new IOException("test suppressed");
            t.addSuppressed(new IOException("test suppressed 2", ioException));
            LOG.error("Error message {}, suppressed?", "Hi", t);
            LOG.error("Error message {}, suppressed?", "Hi", new IOException("test", t));
        }
    }
