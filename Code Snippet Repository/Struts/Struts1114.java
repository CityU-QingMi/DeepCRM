    public void testBadInheritance() throws ConfigurationException {
        final String filename = "com/opensymphony/xwork2/config/providers/xwork-test-bad-inheritance.xml";
        ConfigurationProvider provider = null;
        try {
	    	provider = buildConfigurationProvider(filename);
	    	fail("Should have thrown a ConfigurationException");
	        provider.init(configuration);
	        provider.loadPackages();
        } catch (ConfigurationException e) {
        	// Expected
        }
    }
