    @Test
    public void testAppender() throws Exception {
        // TODO Is there a better way to test than putting the thread to sleep all over the place?
        final Logger logger = loggerContextRule.getLogger();
        final File file = new File(FILE);
        assertTrue("Log file does not exist", file.exists());
        logger.debug("This is test message number 1, waiting for rolling");

        final RollingFileAppender app = (RollingFileAppender) loggerContextRule.getLoggerContext().getConfiguration().getAppender("RollingFile");
        final TriggeringPolicy policy = app.getManager().getTriggeringPolicy();
        assertNotNull("No triggering policy", policy);
        assertTrue("Incorrect policy type", policy instanceof CronTriggeringPolicy);
        final CronExpression expression = ((CronTriggeringPolicy) policy).getCronExpression();
        assertEquals("Incorrect cron expresion", cronExpression, expression.getCronExpression());
        logger.debug("Cron expression will be {}", expression.getCronExpression());

        // force a reconfiguration
        for (int i = 1; i <= 20; ++i) {
            logger.debug("Adding first event {}", i);
        }

        Thread.sleep(remainingTime);
        final File dir = new File(DIR);
        assertTrue("Directory not created", dir.exists() && dir.listFiles().length > 0);

        for (int i = 1; i < 5; i++) {
          logger.debug("Adding some more event {}", i);
          Thread.sleep(1000);
        }
        final Matcher<File> hasGzippedFile = hasName(that(endsWith(".gz")));
        int count = 0;
        final File[] files = dir.listFiles();
        for (final File generatedFile : files) {
          if (hasGzippedFile.matches(generatedFile)) {
              count++;
          }
        }

        assertNotEquals("No compressed files found", 0, count);
        assertEquals("Multiple files found" , 1, count);
    }
