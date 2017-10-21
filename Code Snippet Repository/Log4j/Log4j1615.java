    public static void main(final String[] args) {
        try (final LoggerContext ctx = Configurator.initialize(FormatterLoggerManualExample.class.getName(),
                "target/test-classes/log4j2-console.xml");) {
            final User user = new User();
            logger.debug("User %s with birthday %s", user.getName(), user.getBirthdayCalendar());
            logger.debug("User %1$s with birthday %2$tm %2$te, %2$tY", user.getName(), user.getBirthdayCalendar());
            logger.debug("Integer.MAX_VALUE = %,d", Integer.MAX_VALUE);
            logger.debug("Long.MAX_VALUE = %,d", Long.MAX_VALUE);
        }

    }
