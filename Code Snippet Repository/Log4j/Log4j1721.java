    @Test
    public void testAppendDoesNotOverwriteExistingFile() throws IOException {
        final File file = File.createTempFile("log4j2", "test");
        file.deleteOnExit();
        assertEquals(0, file.length());

        final int initialLength = 4 * 1024;

        // create existing file
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(new byte[initialLength], 0, initialLength);
            fos.flush();
        }
        assertEquals("all flushed to disk", initialLength, file.length());

        final boolean isAppend = true;
        final boolean immediateFlush = false;
        try (final MemoryMappedFileManager manager = MemoryMappedFileManager.getFileManager(file.getAbsolutePath(),
                isAppend, immediateFlush, MemoryMappedFileManager.DEFAULT_REGION_LENGTH, null, null)) {
            manager.writeBytes(new byte[initialLength], 0, initialLength);
        }
        final int expected = initialLength * 2;
        assertEquals("appended, not overwritten", expected, file.length());
    }
