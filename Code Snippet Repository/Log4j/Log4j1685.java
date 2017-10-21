    public void test(final String[] args) {
        // System.out.println(System.getProperty("java.class.path"));
        final String config = args == null || args.length == 0 ? "target/test-classes/log4j2-console-msg-ansi.xml"
                : args[0];
        try (final LoggerContext ctx = Configurator.initialize(ConsoleAppenderAnsiMessagesMain.class.getName(),
                config)) {
            final Logger logger = LogManager.getLogger(ConsoleAppenderJAnsiMessageMain.class);
            logger.info(ansi().fg(RED).a("Hello").fg(CYAN).a(" World").reset());
            // JAnsi format:
            // logger.info("@|red Hello|@ @|cyan World|@");
            for (final Entry<Object, Object> entry : System.getProperties().entrySet()) {
                logger.info("@|KeyStyle {}|@ = @|ValueStyle {}|@", entry.getKey(), entry.getValue());
            }
        }
    }
