    public void testNoConfigurationReload() {
        configProviderMock.expectAndReturn("needsReload", Boolean.FALSE);
        // now check that it doesn't try to reload
        configuration = configurationManager.getConfiguration();

        configProviderMock.verify();

        // this will be called in teardown
        configProviderMock.expect("destroy");
    }
