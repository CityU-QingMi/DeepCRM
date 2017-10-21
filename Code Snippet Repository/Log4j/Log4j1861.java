    @Test
    public void testAppender() throws Exception {
        final File dir = new File(DIR);
        dir.mkdirs();
        for (int i = 1; i <= 30; i++) {
            final String day = i < 10 ? "0" + i : "" + i;
            new File(dir, "test-201511" + day + "-0.log").createNewFile();
        }
        assertEquals("Dir " + DIR + " filecount", 30, dir.listFiles().length);

        final Logger logger = loggerContextRule.getLogger();
        // Trigger the rollover
        while (dir.listFiles().length < 32) {
            // 60+ chars per message: each message should trigger a rollover
            logger.debug("This is a very, very, very, very long test message............."); // 60+ chars:
            Thread.sleep(100); // Allow time for rollover to complete
        }

        final File[] files = dir.listFiles();
        for (final File file : files) {
            System.out.println(file);
        }
        for (final File file : files) {
            assertTrue(file.getName() + " starts with 'test-'", file.getName().startsWith("test-"));
            assertTrue(file.getName() + " ends with '.log'", file.getName().endsWith(".log"));
            final String strDate = file.getName().substring(5, 13);
            assertFalse(file + " is not Fri 13th", strDate.endsWith("20151113"));
        }
    }
