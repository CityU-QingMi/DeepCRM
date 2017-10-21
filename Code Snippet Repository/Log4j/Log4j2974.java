    @Test
    public void testGetDefaultLogger01() throws Exception {
        final Object page = new Object() {};
        this.setUp(page);

        assertNull("The default logger should be null.", TagUtils.getDefaultLogger(this.context));

        final Log4jTaglibLogger returned = this.tag.getLogger();
        assertNotNull("The first returned logger should not be null.", returned);
        assertEquals("The logger name is not correct.", page.getClass().getName(), returned.getName());

        final Log4jTaglibLogger defaultLogger = TagUtils.getDefaultLogger(this.context);
        assertNotNull("The default logger should not be null anymore.", defaultLogger);
        assertSame("The default logger should be the same as the returned logger.", returned, defaultLogger);

        final Log4jTaglibLogger returned2 = this.tag.getLogger();

        assertNotNull("The second returned logger should not be null.", returned2);
        assertSame("The second returned logger should be the same as the first.", returned, returned2);
    }
