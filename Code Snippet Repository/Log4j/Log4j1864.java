    @Test
    public void testRollingFileAppenderWithReconfigure() throws Exception {
        logger.debug("Before reconfigure");

        @SuppressWarnings("resource") // managed by the rule.
        final LoggerContext context = loggerContextRule.getLoggerContext();
        Configuration config = context.getConfiguration();
        context.setConfigLocation(new URI(CONFIG));
        context.reconfigure();
        logger.debug("Force a rollover");
        final File dir = new File(DIR);
        for (int i = 0; i < MAX_TRIES; ++i) {
            Thread.sleep(200);
            if (config != context.getConfiguration()) {
                break;
            }
        }

        assertTrue("Directory not created", dir.exists() && dir.listFiles().length > 0);
        final File[] files = dir.listFiles();
        assertNotNull(files);
        assertThat(dir.listFiles().length, is(equalTo(2)));
    }
