    @Test
    public void testAppender() throws Exception {
        // create some files that match the glob but exceed maxDepth
        final Path p1 = writeTextTo(DIR + "/1/test-4.log"); // glob="**/test-4.log"
        final Path p2 = writeTextTo(DIR + "/2/test-4.log");
        final Path p3 = writeTextTo(DIR + "/1/2/test-4.log");
        final Path p4 = writeTextTo(DIR + "/1/2/3/test-4.log");

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
        final List<String> expected = Arrays.asList("1", "2", "test-1.log", "test-2.log", "test-3.log");
        assertEquals(Arrays.toString(files), expected.size(), files.length);
        for (final File file : files) {
            assertTrue("test-4.log should have been deleted",
                    expected.contains(file.getName()));
        }

        assertTrue(p1 + " should not have been deleted", Files.exists(p1));
        assertTrue(p2 + " should not have been deleted", Files.exists(p2));
        assertTrue(p3 + " should not have been deleted", Files.exists(p3));
        assertTrue(p4 + " should not have been deleted", Files.exists(p4));
    }
