    @Test
    public void testAppender() throws Exception {
        final Logger logger = loggerContextRule.getLogger();
        logger.debug("This is test message number 1");
        Thread.sleep(1500);
        // Trigger the rollover
        for (int i = 0; i < 16; ++i) {
            logger.debug("This is test message number " + i + 1);
        }
        final File dir = new File(DIR);
        assertTrue("Directory not created", dir.exists() && dir.listFiles().length > 0);

        final int MAX_TRIES = 20;
        final Matcher<File[]> hasGzippedFile = hasItemInArray(that(hasName(that(endsWith(".gz")))));
        for (int i = 0; i < MAX_TRIES; i++) {
            final File[] files = dir.listFiles();
            if (hasGzippedFile.matches(files)) {
                return; // test succeeded
            }
            logger.debug("Adding additional event " + i);
            Thread.sleep(100); // Allow time for rollover to complete
        }
        fail("No compressed files found");
    }
