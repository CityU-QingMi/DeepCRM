    @Test
    public void testAppender() throws Exception {
        final int LINECOUNT = 18; // config has max="100"
        for (int i = 0; i < LINECOUNT; ++i) {
            // 30 chars per message: each message triggers a rollover
            logger.debug("This is a test message number " + i); // 30 chars:
        }
        Thread.sleep(100); // Allow time for rollover to complete

        assertTrue("Dir " + directory + " should exist", directory.exists());
        assertTrue("Dir " + directory + " should contain files", directory.listFiles().length > 0);

        int total = 0;
        for (final File file : directory.listFiles()) {
            final List<String> lines = Files.readAllLines(file.toPath(), Charset.defaultCharset());
            total += lines.size();
        }
        assertEquals("rolled over lines", LINECOUNT - 1, total);
    }
