    @Test
    public void testFileTimeBasedOnFileModifiedTimeWhenAppendIsTrue() throws IOException {
        final File file = File.createTempFile("log4j2", "test");
        file.deleteOnExit();
        LockSupport.parkNanos(1000000); // 1 millisec

        final boolean isAppend = true;
        assertThat(file, lastModified(beforeNow()));

        final RollingRandomAccessFileManager manager = RollingRandomAccessFileManager.getRollingRandomAccessFileManager(
                //
                file.getAbsolutePath(), Strings.EMPTY, isAppend, true,
                RollingRandomAccessFileManager.DEFAULT_BUFFER_SIZE, new SizeBasedTriggeringPolicy(Long.MAX_VALUE), //
                null, null, null, null, null, null, null);
        assertThat(file, lastModified(equalTo(manager.getFileTime())));
    }
