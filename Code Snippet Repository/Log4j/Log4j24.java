    @Test
    public void testExistingLog4j2Logger() {
        // create the logger using LogManager
        org.apache.logging.log4j.LogManager.getLogger("existingLogger");
        // Logger will be the one created above
        final Logger logger = Logger.getLogger("existingLogger");
        final Logger l2 = LogManager.getLogger("existingLogger");
        assertEquals(logger, l2);
        logger.setLevel(Level.ERROR);
        final Priority debug = Level.DEBUG;
        // the next line will throw an exception if the LogManager loggers
        // aren't supported by 1.2 Logger/Category
        logger.l7dlog(debug, "Hello, World", new Object[0], null);
        assertTrue(appender.getEvents().size() == 0);
    }
