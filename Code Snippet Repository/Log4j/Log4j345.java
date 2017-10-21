    @Test
    public void testGetLogger() {
        Logger logger = LogManager.getLogger();
        assertNotNull("No Logger returned", logger);
        assertTrue("Incorrect Logger name: " + logger.getName(),LogManagerTest.class.getName().equals(logger.getName()));
        logger = LogManager.getLogger(ParameterizedMessageFactory.INSTANCE);
        assertNotNull("No Logger returned", logger);
        assertTrue("Incorrect Logger name: " + logger.getName(),LogManagerTest.class.getName().equals(logger.getName()));
        logger = LogManager.getLogger((Class<?>) null);
        assertNotNull("No Logger returned", logger);
        assertTrue("Incorrect Logger name: " + logger.getName(),LogManagerTest.class.getName().equals(logger.getName()));
        logger = LogManager.getLogger((Class<?>) null, ParameterizedMessageFactory.INSTANCE);
        assertNotNull("No Logger returned", logger);
        assertTrue("Incorrect Logger name: " + logger.getName(),LogManagerTest.class.getName().equals(logger.getName()));
        logger = LogManager.getLogger((String) null);
        assertNotNull("No Logger returned", logger);
        assertTrue("Incorrect Logger name: " + logger.getName(),LogManagerTest.class.getName().equals(logger.getName()));
        logger = LogManager.getLogger((String) null, ParameterizedMessageFactory.INSTANCE);
        assertNotNull("No Logger returned", logger);
        assertTrue("Incorrect Logger name: " + logger.getName(),LogManagerTest.class.getName().equals(logger.getName()));
        logger = LogManager.getLogger((Object) null);
        assertNotNull("No Logger returned", logger);
        assertTrue("Incorrect Logger name: " + logger.getName(),LogManagerTest.class.getName().equals(logger.getName()));
        logger = LogManager.getLogger((Object) null, ParameterizedMessageFactory.INSTANCE);
        assertNotNull("No Logger returned", logger);
        assertTrue("Incorrect Logger name: " + logger.getName(),LogManagerTest.class.getName().equals(logger.getName()));
    }
