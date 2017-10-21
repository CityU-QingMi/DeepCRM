    private static void writer(final boolean locking, final int logEventCount, final String name, final boolean createOnDemand,
            final boolean concurrent) throws Exception {
        final Layout<String> layout = createPatternLayout();
        // @formatter:off
        final FileAppender appender = FileAppender.newBuilder()
            .withFileName(FILE_NAME)
            .withName("test")
            .withImmediateFlush(false)
            .withIgnoreExceptions(false)
            .withLocking(locking)
            .withBufferedIo(false)
            .withLayout(layout)
            .withCreateOnDemand(createOnDemand)
            .build();
        // @formatter:on
        Assert.assertEquals(createOnDemand, appender.getManager().isCreateOnDemand());
        try {
            appender.start();
            assertTrue("Appender did not start", appender.isStarted());
            final boolean exists = Files.exists(PATH);
            final String msg = String.format("concurrent = %s, createOnDemand = %s, file exists = %s", concurrent, createOnDemand,
                    exists);
            // If concurrent the file might have been created (or not.)
            // Can't really test createOnDemand && concurrent.
            final boolean expectFileCreated = !createOnDemand;
            if (concurrent && expectFileCreated) {
                Assert.assertTrue(msg, exists);
            } else if (expectFileCreated) {
                Assert.assertNotEquals(msg, createOnDemand, exists);
            }
            for (int i = 0; i < logEventCount; ++i) {
                final LogEvent logEvent = Log4jLogEvent.newBuilder().setLoggerName("TestLogger")
                        .setLoggerFqcn(FileAppenderTest.class.getName()).setLevel(Level.INFO)
                        .setMessage(new SimpleMessage("Test")).setThreadName(name)
                        .setTimeMillis(System.currentTimeMillis()).build();
                try {
                    appender.append(logEvent);
                    Thread.sleep(25); // Give up control long enough for another thread/process to occasionally do
                                      // something.
                } catch (final Exception ex) {
                    throw ex;
                }
            }
        } finally {
            appender.stop();
        }
        assertFalse("Appender did not stop", appender.isStarted());
    }
