    @Test
    public void testWrite_multiplesOfBufferSize() throws IOException {
        final File file = File.createTempFile("log4j2", "test");
        file.deleteOnExit();
        try (final RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            final OutputStream os = NullOutputStream.getInstance();
            final boolean append = false;
            final boolean flushNow = false;
            final long triggerSize = Long.MAX_VALUE;
            final long time = System.currentTimeMillis();
            final TriggeringPolicy triggerPolicy = new SizeBasedTriggeringPolicy(triggerSize);
            final RolloverStrategy rolloverStrategy = null;
            final RollingRandomAccessFileManager manager = new RollingRandomAccessFileManager(null, raf,
                    file.getName(), Strings.EMPTY, os, append, flushNow,
                    RollingRandomAccessFileManager.DEFAULT_BUFFER_SIZE, triggerSize, time, triggerPolicy, rolloverStrategy,
                    null, null, null, null, null, true);

            final int size = RollingRandomAccessFileManager.DEFAULT_BUFFER_SIZE * 3;
            final byte[] data = new byte[size];
            manager.write(data, 0, data.length, flushNow); // no buffer overflow exception

            // buffer is full but not flushed yet
            assertEquals(RollingRandomAccessFileManager.DEFAULT_BUFFER_SIZE * 3, raf.length());
        }
    }
