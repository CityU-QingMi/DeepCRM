    public void testInvalidFileThrowsException() {
        final String filename = "com/opensymphony/xwork2/config/providers/xwork-test-invalid-file.xml";

        try {
            ConfigurationProvider provider = buildConfigurationProvider(filename);
            fail();
        } catch (ConfigurationException e) {
            // this is what we expect
        }
    }
