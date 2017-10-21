    public void test(final String[] args) {
        // System.out.println(System.getProperty("java.class.path"));
        final String config = args == null || args.length == 0 ? "target/test-classes/log4j2-console-xex-ansi.xml"
                : args[0];
        final LoggerContext ctx = Configurator.initialize(ConsoleAppenderAnsiMessagesMain.class.getName(), config);
        final Logger logger = LogManager.getLogger(ConsoleAppenderJAnsiXExceptionMain.class);
        try {
            Files.getFileStore(Paths.get("?BOGUS?"));
        } catch (final Exception e) {
            final IllegalArgumentException logE = new IllegalArgumentException("Bad argument foo", e);
            logger.error("Gotcha!", logE);
        } finally {
            Configurator.shutdown(ctx);
        }
    }
