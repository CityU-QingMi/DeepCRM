    @Test
    public void testRandomAccessConfiguration() throws Exception {
        final Logger logger = this.init.getLogger("com.foo.Bar");
        final String message = "This is a test log message brought to you by Slurm.";
        logger.info(message);
        this.init.getLoggerContext().stop(); // stop async thread

        String line;
        try (final BufferedReader reader = new BufferedReader(new FileReader(this.logFile))) {
            line = reader.readLine();
        }
        assertNotNull(line);
        assertThat(line, containsString(message));
        final Matcher<String> containsLocationInformation = containsString("testRandomAccessConfiguration");
        final Matcher<String> containsLocationInformationIfEnabled = this.locationEnabled ?
                containsLocationInformation : not(containsLocationInformation);
        assertThat(line, containsLocationInformationIfEnabled);
    }
