    public void testConfigurationReload() {
        // now check that it reloads
        configProviderMock.expectAndReturn("needsReload", Boolean.TRUE);
        configProviderMock.expect("init", C.isA(Configuration.class));
        configProviderMock.expect("register", C.ANY_ARGS);
        configProviderMock.expect("loadPackages", C.ANY_ARGS);
        configProviderMock.expect("destroy", C.ANY_ARGS);
        configProviderMock.matchAndReturn("toString", "mock");
        configuration.getContainer().getInstance(FileManagerFactory.class).getFileManager().setReloadingConfigs(true);
        configuration = configurationManager.getConfiguration();
        configProviderMock.verify();

        // this will be called in teardown
        configProviderMock.expect("destroy");
    }
