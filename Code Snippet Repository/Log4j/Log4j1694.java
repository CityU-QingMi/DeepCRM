    @Test
    public void testFilePermissionsAPI() throws Exception {
        final File file = new File(DIR, "AppenderTest-" + fileIndex + ".log");
        final Path path = file.toPath();
        final Layout<String> layout = PatternLayout.newBuilder().withPattern(PatternLayout.SIMPLE_CONVERSION_PATTERN)
                .build();
        // @formatter:off
        final FileAppender appender = FileAppender.newBuilder()
            .withFileName(file.getAbsolutePath())
            .withName("test")
            .withImmediateFlush(false)
            .withIgnoreExceptions(false)
            .withBufferedIo(false)
            .withBufferSize(1)
            .withLayout(layout)
            .withCreateOnDemand(createOnDemand)
            .withFilePermissions(filePermissions)
            .build();
        // @formatter:on
        try {
            appender.start();
            assertTrue("Appender did not start", appender.isStarted());
            Assert.assertNotEquals(createOnDemand, Files.exists(path));
            long curLen = file.length();
            long prevLen = curLen;
            assertTrue("File length: " + curLen, curLen == 0);
            for (int i = 0; i < 100; ++i) {
                final LogEvent event = Log4jLogEvent.newBuilder().setLoggerName("TestLogger") //
                        .setLoggerFqcn(FileAppenderPermissionsTest.class.getName()).setLevel(Level.INFO) //
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
            assertEquals(filePermissions, PosixFilePermissions.toString(Files.getPosixFilePermissions(path)));
        } finally {
            appender.stop();
        }
        assertFalse("Appender did not stop", appender.isStarted());
    }
