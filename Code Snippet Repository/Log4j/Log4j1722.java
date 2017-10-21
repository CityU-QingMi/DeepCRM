    @Test
    public void testOutputStreamAppenderToBufferedOutputStream() throws SQLException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final OutputStream os = new BufferedOutputStream(out);
        final String name = getName(out);
        final Logger logger = LogManager.getLogger(name);
        addAppender(os, name);
        logger.error(TEST_MSG);
        final String actual = out.toString();
        Assert.assertTrue(actual, actual.contains(TEST_MSG));
    }
