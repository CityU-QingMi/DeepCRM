    public void testInvalidActions() throws Exception {
        final String filename = "com/opensymphony/xwork2/config/providers/xwork-test-action-invalid.xml";

        try {
            ConfigurationProvider provider = buildConfigurationProvider(filename);
            fail("Should have thrown an exception");
        } catch (ConfigurationException ex) {
            // it worked correctly
        }
    }
