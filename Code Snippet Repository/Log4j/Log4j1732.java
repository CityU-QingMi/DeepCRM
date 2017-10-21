    @Test
    public void testConfigurableBufferSize() throws IOException {
        final File file = folder.newFile();
        try (final RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            final OutputStream os = NullOutputStream.getInstance();
            final int bufferSize = 4 * 1024;
            assertNotEquals(bufferSize, RandomAccessFileManager.DEFAULT_BUFFER_SIZE);

            final RandomAccessFileManager manager = new RandomAccessFileManager(null, raf, file.getName(),
                    os, bufferSize, null, null, true);

            // check the resulting buffer size is what was requested
            assertEquals(bufferSize, manager.getBufferSize());
        }}
