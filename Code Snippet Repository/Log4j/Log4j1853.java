    @Test
    public void testAppender() throws Exception {
        // TODO Is there a better way to test than putting the thread to sleep all over the place?
        final Logger logger = loggerContextRule.getLogger();
        final File file = new File(FILE);
        assertTrue("Log file does not exist", file.exists());
        logger.debug("This is test message number 1");
        Thread.sleep(2500);
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
        final Path src = FileSystems.getDefault().getPath("target/test-classes/log4j-rolling-cron2.xml");
        try (final OutputStream os = new FileOutputStream("target/test-classes/log4j-rolling-cron.xml")) {
            Files.copy(src, os);
        }
        Thread.sleep(5000);
        // force a reconfiguration
        for (int i = 0; i < MAX_TRIES; ++i) {
            logger.debug("Adding new event {}", i);
        }
        Thread.sleep(1000);
        final RollingFileAppender app = (RollingFileAppender) loggerContextRule.getLoggerContext().getConfiguration().getAppender("RollingFile");
        final TriggeringPolicy policy = app.getManager().getTriggeringPolicy();
        assertNotNull("No triggering policy", policy);
        assertTrue("Incorrect policy type", policy instanceof CronTriggeringPolicy);
        final CronExpression expression = ((CronTriggeringPolicy) policy).getCronExpression();
        assertTrue("Incorrect triggering policy", expression.getCronExpression().equals("* * * ? * *"));

    }
