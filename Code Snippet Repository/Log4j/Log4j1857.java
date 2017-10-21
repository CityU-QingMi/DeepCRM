    @Test
    public void testAppender() throws Exception {

        final Logger logger = loggerContextRule.getLogger();
        for (int i = 0; i < 10; ++i) {
            // 30 chars per message: each message triggers a rollover
            logger.debug("This is a test message number " + i); // 30 chars:
        }
        Thread.sleep(100); // Allow time for rollover to complete

        final File dir = new File(DIR);
        assertTrue("Dir " + DIR + " should exist", dir.exists());
        assertTrue("Dir " + DIR + " should contain files", dir.listFiles().length > 0);

        final File[] files = dir.listFiles();
        for (final File file : files) {
            System.out.println(file + " (" + file.length() + "B) "
                    + FixedDateFormat.create(FixedFormat.ABSOLUTE).format(file.lastModified()));
        }
        assertEquals(Arrays.toString(files), 4, files.length);
        long total = 0;
        for (final File file : files) {
            // sometimes test-6.log remains
            assertTrue("unexpected file " + file, file.getName().startsWith("test-"));
            total += file.length();
        }
        assertTrue("accumulatedSize=" + total, total <= 500);
    }
