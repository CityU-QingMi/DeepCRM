    @Test
    public void testConfigurableBufferSize() throws IOException {
        final File file = File.createTempFile("log4j2", "test");
        file.deleteOnExit();
        try (final RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            final OutputStream os = NullOutputStream.getInstance();
            final boolean append = false;
            final boolean flushNow = false;
            final long triggerSize = 0;
            final long time = System.currentTimeMillis();
            final TriggeringPolicy triggerPolicy = new SizeBasedTriggeringPolicy(triggerSize);
            final int bufferSize = 4 * 1024;
            assertNotEquals(bufferSize, RollingRandomAccessFileManager.DEFAULT_BUFFER_SIZE);
            final RolloverStrategy rolloverStrategy = null;
            final RollingRandomAccessFileManager manager = new RollingRandomAccessFileManager(null, raf,
                    file.getName(), Strings.EMPTY, os, append, flushNow, bufferSize, triggerSize, time, triggerPolicy,
                    rolloverStrategy, null, null, null, null, null, true);

            // check the resulting buffer size is what was requested
            assertEquals(bufferSize, manager.getBufferSize());
        }
    }
