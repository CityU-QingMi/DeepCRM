    @Test
    public void testGetLoggerWithStringLogger() throws Exception {
        this.setUp(null);

        this.tag.setLogger("testGetLoggerWithStringLogger");
        final Log4jTaglibLogger returned = this.tag.getLogger();

        assertNotNull("The first returned logger should not be null.", returned);
        assertEquals("The name is not correct.", "testGetLoggerWithStringLogger", returned.getName());

        final Log4jTaglibLogger returned2 = this.tag.getLogger();

        assertNotNull("The second returned logger should not be null.", returned2);
        assertSame("The second returned logger should be the same as the first.", returned, returned2);
    }
