    private Configuration configuration(final String packageName) {
        final Configuration mock = createNiceMock(Configuration.class);

        packageConfiguration = packageConfiguration();
        expect(mock.getPackageConfig(packageName)).andStubReturn(packageConfiguration);
        RuntimeConfiguration runtime = createNiceMock(RuntimeConfiguration.class);
        expect(runtime.getActionConfig("", "index")).andStubReturn(new ActionConfig.Builder("", "index", "").build());
        expect(mock.getRuntimeConfiguration()).andStubReturn(runtime);

        replay(mock, runtime);

        return mock;
    }
