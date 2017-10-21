    @Test
    public void testAppendDoesNotOverwriteExistingFile() throws IOException {
        final boolean isAppend = true;
        final File file = folder.newFile();
        assertEquals(0, file.length());

        final byte[] bytes = new byte[4 * 1024];

        // create existing file
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(bytes, 0, bytes.length);
            fos.flush();
        }
        assertEquals("all flushed to disk", bytes.length, file.length());

        final RandomAccessFileManager manager = RandomAccessFileManager.getFileManager(
                file.getAbsolutePath(), isAppend, true, RandomAccessFileManager.DEFAULT_BUFFER_SIZE, null, null, null);
        manager.write(bytes, 0, bytes.length, true);
        final int expected = bytes.length * 2;
        assertEquals("appended, not overwritten", expected, file.length());
    }
