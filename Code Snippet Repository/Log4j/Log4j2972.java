    @Test
    public void testGetLoggerWithTaglibLogger() throws Exception {
        this.setUp(null);

        final AbstractLogger wrapped = (AbstractLogger)LogManager.getLogger("testGetLoggerWithTaglibLogger");
        final Log4jTaglibLogger logger = new Log4jTaglibLogger(wrapped, wrapped.getName(), wrapped.getMessageFactory());

        this.tag.setLogger(logger);
        Log4jTaglibLogger returned = this.tag.getLogger();

        assertNotNull("The first returned logger should not be null.", returned);
        assertSame("The first returned logger should be the same as the set.", logger, returned);
        assertEquals("The name is not correct.", "testGetLoggerWithTaglibLogger", returned.getName());

        returned = this.tag.getLogger();

        assertNotNull("The second returned logger should not be null.", returned);
        assertSame("The second returned logger should be the same as the set.", logger, returned);
    }
