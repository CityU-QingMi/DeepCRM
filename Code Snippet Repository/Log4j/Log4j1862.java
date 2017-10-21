    @Test
    public void testAppender() throws Exception {
        final Logger logger = loggerContextRule.getLogger();
        // Trigger the rollover
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
            System.out.println(file);
        }
        for (final File file : files) {
            assertTrue(file.getName() + " starts with 'test-'", file.getName().startsWith("test-"));
            assertTrue(file.getName() + " ends with '.log'", file.getName().endsWith(".log"));
            final String strIndex = file.getName().substring(5, file.getName().indexOf('.'));
            final int index = Integer.parseInt(strIndex);
            assertTrue(file + " should have odd index", index % 2 == 1);
        }
    }
