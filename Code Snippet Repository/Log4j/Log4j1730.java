    @Test
    public void testWrite_multiplesOfBufferSize() throws IOException {
        final File file = folder.newFile();
        try (final RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            final OutputStream os = NullOutputStream.getInstance();
            final RandomAccessFileManager manager = new RandomAccessFileManager(null, raf, file.getName(),
                    os, RandomAccessFileManager.DEFAULT_BUFFER_SIZE, null, null, true);

            final int size = RandomAccessFileManager.DEFAULT_BUFFER_SIZE * 3;
            final byte[] data = new byte[size];
            manager.write(data); // no buffer overflow exception

            // all data is written if exceeds buffer size
            assertEquals(RandomAccessFileManager.DEFAULT_BUFFER_SIZE * 3, raf.length());
        }}
