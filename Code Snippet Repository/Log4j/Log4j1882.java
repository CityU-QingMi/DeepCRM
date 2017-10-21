    @Ignore
    @Test
    public void testAppender() throws Exception {
        for (int i = 0; i < 8; ++i) {
            logger.debug("This is test message number " + i);
        }
        Thread.sleep(50);
        final File dir = new File(DIR);
        assertTrue("Directory not created: " + dir, dir.exists() );
        assertTrue("Directory empty: " + dir, dir.listFiles().length > 0);
        final File[] files = dir.listFiles();
        assertNotNull(files);
        for (final File file : files) {
            assertHeader(file);
            assertFooter(file);
        }
        final File logFile = new File(LOGFILE);
        assertTrue("Expected logfile to exist: " + LOGFILE, logFile.exists());
        assertHeader(logFile);
    }
