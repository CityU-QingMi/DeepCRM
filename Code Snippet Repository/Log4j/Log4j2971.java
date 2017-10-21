    @Test
    public void testGetLoggerWithGenericLogger() throws Exception {
        this.setUp(null);

        final Logger logger = LogManager.getLogger("testGetLoggerWithGenericLogger");

        this.tag.setLogger(logger);
        final Log4jTaglibLogger returned = this.tag.getLogger();

        assertNotNull("The first returned logger should not be null.", returned);
        assertNotSame("The first returned logger should not be the same as the set.", logger, returned);
        assertEquals("The name is not correct.", "testGetLoggerWithGenericLogger", returned.getName());

        final Log4jTaglibLogger returned2 = this.tag.getLogger();

        assertNotNull("The second returned logger should not be null.", returned2);
        assertSame("The second returned logger should be the same as the first.", returned, returned2);

        this.tag.release();

        final Log4jTaglibLogger returned3 = this.tag.getLogger();

        assertNotNull("The third returned logger should not be null.", returned3);
        assertNotSame("The third returned logger should not be the same as the first.", returned, returned3);
    }
