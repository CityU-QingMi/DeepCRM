    @Test
    public void testAppender() throws Exception {
        final Path p1 = writeTextTo(DIR + "/my-1.log"); // glob="test-*.log"
        final Path p2 = writeTextTo(DIR + "/my-2.log");
        final Path p3 = writeTextTo(DIR + "/my-3.log");
        final Path p4 = writeTextTo(DIR + "/my-4.log");
        final Path p5 = writeTextTo(DIR + "/my-5.log");

        final Logger logger = loggerContextRule.getLogger();
        for (int i = 0; i < 10; ++i) {
            updateLastModified(p1, p2, p3, p4, p5); // make my-*.log files most recent

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
        
        final List<String> expected = Arrays.asList("my-1.log", "my-2.log", "my-3.log", "my-4.log", "my-5.log");
        assertEquals(Arrays.toString(files), expected.size() + 3, files.length);
        for (final File file : files) {
            if (!expected.contains(file.getName()) && !file.getName().startsWith("test-")) {
                fail("unexpected file" + file);
            }
        }
    }
