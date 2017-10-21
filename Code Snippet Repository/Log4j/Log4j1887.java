    @Test
    public void testAppendDoesNotOverwriteExistingFile() throws IOException {
        final boolean isAppend = true;
        final File file = File.createTempFile("log4j2", "test");
        file.deleteOnExit();
        assertThat(file, isEmpty());

        final byte[] bytes = new byte[4 * 1024];

        // create existing file
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(bytes, 0, bytes.length);
            fos.flush();
        } finally {
            Closer.closeSilently(fos);
        }
        assertThat("all flushed to disk", file, hasLength(bytes.length));

        final boolean immediateFlush = true;
        final RollingRandomAccessFileManager manager = RollingRandomAccessFileManager.getRollingRandomAccessFileManager(
                //
                file.getAbsolutePath(), Strings.EMPTY, isAppend, immediateFlush,
                RollingRandomAccessFileManager.DEFAULT_BUFFER_SIZE, new SizeBasedTriggeringPolicy(Long.MAX_VALUE), //
                null, null, null, null, null, null, null);
        manager.write(bytes, 0, bytes.length, immediateFlush);
        final int expected = bytes.length * 2;
        assertThat("appended, not overwritten", file, hasLength(expected));
    }
