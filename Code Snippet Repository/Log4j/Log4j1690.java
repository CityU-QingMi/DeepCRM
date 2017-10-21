    public void testNoNulCharacters(final String message, final String expected) throws IOException {
        @SuppressWarnings("resource")
        final LoggerContext loggerContext = loggerContextRule.getLoggerContext();
        final Logger logger = loggerContext.getLogger("com.example");
        logger.error("log:", message);
        loggerContext.stop();
        final File file = new File(FILE_PATH);
        final byte[] contents = Files.toByteArray(file);
        int count0s = 0;
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < contents.length; i++) {
            final byte b = contents[i];
            if (b == 0) {
                sb.append(i);
                sb.append(", ");
                count0s++;
            }
        }
        Assert.assertEquals("File contains " + count0s + " 0x00 byte at indices " + sb, 0, count0s);
        final List<String> readLines = Files.readLines(file, Charset.defaultCharset());
        final String actual = readLines.get(0);
        // Assert.assertTrue(actual, actual.contains(message));
        Assert.assertEquals(actual, expected, actual);
        Assert.assertEquals(1, readLines.size());
    }
