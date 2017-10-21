    public static void main(final String[] args) throws Exception {
        final File file = new File(CONFIG);
        try (final LoggerContext ctx = Configurator.initialize("LogTest", LogRolloverTest.class.getClassLoader(),
                file.toURI())) {
            final Logger logger = LogManager.getLogger("TestLogger");

            for (long i = 0;; i += 1) {
                logger.debug("Sequence: " + i);
                Thread.sleep(250);
            }
        }
    }
