    @Test
    public void testOutputStreamAppenderToByteArrayOutputStream() throws SQLException {
        final OutputStream out = new ByteArrayOutputStream();
        final String name = getName(out);
        final Logger logger = LogManager.getLogger(name);
        addAppender(out, name);
        logger.error(TEST_MSG);
        final String actual = out.toString();
        Assert.assertTrue(actual, actual.contains(TEST_MSG));
    }
