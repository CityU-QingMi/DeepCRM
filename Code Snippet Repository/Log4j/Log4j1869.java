    @Test
    public void testReconfigure() throws Exception {
        for (int i = 0; i < 500; ++i) {
            final String message = "This is test message number " + i;
            logger.debug(message);
        }

        final File dir = new File(DIR);
        assertTrue("Directory not created", dir.exists());
        final File[] files = dir.listFiles();
        assertThat(files, hasItemInArray(that(hasName(that(endsWith(".current"))))));
        assertThat(files, hasItemInArray(that(hasName(that(endsWith(".rolled"))))));

        final String originalXmlConfig = FileUtils.readFileToString(CONFIG_FILE, "UTF-8");
        try {
            final String updatedXmlConfig = originalXmlConfig.replace("target/rolling1/rollingtest.%i.rolled",
                    "target/rolling1/rollingtest.%i.reconfigured");
            FileUtils.write(CONFIG_FILE, updatedXmlConfig, "UTF-8");

            // Reconfigure
            loggerContextRule.getLoggerContext().reconfigure();

            for (int i = 0; i < 500; ++i) {
                final String message = "This is test message number " + i;
                logger.debug(message);
            }

            final File[] filesAfterReconfigured = dir.listFiles();
            assertThat(filesAfterReconfigured, hasItemInArray(that(hasName(that(endsWith(".reconfigured"))))));
            assertThat(filesAfterReconfigured, hasItemInArray(that(hasName(that(endsWith(".current"))))));
            assertThat(filesAfterReconfigured, hasItemInArray(that(hasName(that(endsWith(".rolled"))))));
        } finally {
            FileUtils.write(CONFIG_FILE, originalXmlConfig, "UTF-8");
        }
    }
