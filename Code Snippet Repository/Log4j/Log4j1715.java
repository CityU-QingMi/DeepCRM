    public static void main(final String[] args) {
        try (final LoggerContext ctx = Configurator.initialize(Jira739Test.class.getName(),
                "target/test-classes/LOG4J2-739.xml")) {
            for (int i = 0; i < 10; i++) {
                LOG.trace("Entering Log4j Example " + i + " times");
                LOG.error("Ohh!Failed!");
                LOG.trace("Exiting Log4j Example." + i + " times");
            }
        }
    }
