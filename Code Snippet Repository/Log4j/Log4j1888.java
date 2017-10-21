    @Test
    public void testFileTimeBasedOnSystemClockWhenAppendIsFalse() throws IOException {
        final File file = File.createTempFile("log4j2", "test");
        file.deleteOnExit();
        LockSupport.parkNanos(1000000); // 1 millisec

        // append is false deletes the file if it exists
        final boolean isAppend = false;
        final long expectedMin = System.currentTimeMillis();
        final long expectedMax = expectedMin + 500;
        assertThat(file, lastModified(lessThanOrEqualTo(expectedMin)));

        final RollingRandomAccessFileManager manager = RollingRandomAccessFileManager.getRollingRandomAccessFileManager(
                //
                file.getAbsolutePath(), Strings.EMPTY, isAppend, true,
                RollingRandomAccessFileManager.DEFAULT_BUFFER_SIZE, new SizeBasedTriggeringPolicy(Long.MAX_VALUE), //
                null, null, null, null, null, null, null);
        assertTrue(manager.getFileTime() < expectedMax);
        assertTrue(manager.getFileTime() >= expectedMin);
    }
