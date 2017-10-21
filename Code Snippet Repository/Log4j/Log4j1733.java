    @Test
    public void testWrite_dataExceedingMinBufferSize() throws IOException {
        final File file = folder.newFile();
        try (final RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            final OutputStream os = NullOutputStream.getInstance();
            final int bufferSize = 1;
            final RandomAccessFileManager manager = new RandomAccessFileManager(null, raf, file.getName(),
                    os, bufferSize, null, null, true);

            final int size = bufferSize * 3 + 1;
            final byte[] data = new byte[size];
            manager.write(data); // no exception
            // all data is written if exceeds buffer size
            assertEquals(bufferSize * 3 + 1, raf.length());

            manager.flush();
            assertEquals(size, raf.length()); // all data written to file now
        }}
