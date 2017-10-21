    @Test
    public void testDoEndTagLoggerDefault() throws Exception {
        this.tag.setLogger(LogManager.getLogger("testDoEndTagLoggerDefault"));

        assertNull("The default logger should be null.", TagUtils.getDefaultLogger(this.context));
        assertEquals("The return value is not correct.", Tag.EVAL_PAGE, this.tag.doEndTag());

        final Log4jTaglibLogger logger = TagUtils.getDefaultLogger(this.context);
        assertNotNull("The default logger should not be null anymore.", logger);
        assertEquals("The logger name is not correct.", "testDoEndTagLoggerDefault", logger.getName());
    }
