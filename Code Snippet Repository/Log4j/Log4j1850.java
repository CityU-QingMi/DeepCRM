    @Test
    public void testAppender() throws Exception {
        // TODO Is there a better way to test than putting the thread to sleep all over the place?
        final Logger logger = loggerContextRule.getLogger();
        final File file = new File(FILE);
        assertTrue("Log file does not exist", file.exists());
        final long end = System.currentTimeMillis() + 5000;
        final Random rand = new SecureRandom();
        rand.setSeed(end);
        int count = 1;
        do {
            logger.debug("Log Message {}", count++);
            Thread.sleep(10 * rand.nextInt(100));
        } while (System.currentTimeMillis() < end);
        final File dir = new File(DIR);
        assertTrue("Directory not created", dir.exists() && dir.listFiles().length > 0);

        final int MAX_TRIES = 20;
        final Matcher<File[]> hasGzippedFile = hasItemInArray(that(hasName(that(endsWith(".gz")))));
        boolean succeeded = false;
        for (int i = 0; i < MAX_TRIES; i++) {
            final File[] files = dir.listFiles();
            if (hasGzippedFile.matches(files)) {
                succeeded = true;
                break;
            }
            logger.debug("Sleeping #" + i);
            Thread.sleep(100); // Allow time for rollover to complete
        }
        if (!succeeded) {
            final File[] files = dir.listFiles();
            for (final File dirFile : files) {
                logger.error("Found file: " + dirFile.getPath());
            }
            fail("No compressed files found");
        }
    }
