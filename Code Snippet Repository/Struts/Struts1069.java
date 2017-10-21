    public void testInitOnceForConfigurationProviders() {
        
        loadConfigurationProviders(new StubConfigurationProvider() {
            boolean called = false;
            @Override
            public void init(Configuration config) {
                if (called) {
                    fail("Called twice");
                }
                called = true;
            }
            
            @Override
            public void loadPackages() {
                if (!called) {
                    fail("Never called");
                }
            }
        });
    }
