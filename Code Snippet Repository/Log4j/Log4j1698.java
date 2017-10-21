    @Test
    public void testSmallestBufferSize() throws Exception {
        final Layout<String> layout = createPatternLayout();
        // @formatter:off
        final FileAppender appender = FileAppender.newBuilder()
            .withFileName(FILE_NAME)
            .withName("test")
            .withImmediateFlush(false)
            .withIgnoreExceptions(false)
            .withBufferedIo(false)
            .withBufferSize(1)
            .withLayout(layout)
            .withCreateOnDemand(createOnDemand)
            .build();
        // @formatter:on
        try {
            appender.start();
            final File file = new File(FILE_NAME);
            assertTrue("Appender did not start", appender.isStarted());
            Assert.assertNotEquals(createOnDemand, Files.exists(PATH));
            long curLen = file.length();
            long prevLen = curLen;
            assertTrue("File length: " + curLen, curLen == 0);
            for (int i = 0; i < 100; ++i) {
                final LogEvent event = Log4jLogEvent.newBuilder().setLoggerName("TestLogger") //
                        .setLoggerFqcn(FileAppenderTest.class.getName()).setLevel(Level.INFO) //
                        .setMessage(new SimpleMessage("Test")).setThreadName(this.getClass().getSimpleName()) //
                        .setTimeMillis(System.currentTimeMillis()).build();
                try {
                    appender.append(event);
                    curLen = file.length();
                    assertTrue("File length: " + curLen, curLen > prevLen);
                    // Give up control long enough for another thread/process to occasionally do something.
                    Thread.sleep(25);
                } catch (final Exception ex) {
                    throw ex;
                }
                prevLen = curLen;
            }
        } finally {
            appender.stop();
        }
        assertFalse("Appender did not stop", appender.isStarted());
    }
