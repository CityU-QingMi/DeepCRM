    public void testMultipleContainerProviders() throws Exception {
        // to start from scratch
        configurationManager.destroyConfiguration();
         // to build basic configuration
        configurationManager.getConfiguration();

        Mock mockContainerProvider = new Mock(ContainerProvider.class);
        mockContainerProvider.expect("init", C.ANY_ARGS);
        mockContainerProvider.expect("register", C.ANY_ARGS);
        mockContainerProvider.matchAndReturn("equals", C.ANY_ARGS, false);
        mockContainerProvider.matchAndReturn("toString", "foo");
        mockContainerProvider.matchAndReturn("destroy", null);
        mockContainerProvider.expectAndReturn("needsReload", true);
        // the order of providers must be changed as just first is checked if reload is needed
        configurationManager.addContainerProvider((ContainerProvider) mockContainerProvider.proxy());
        XmlConfigurationProvider provider = new XmlConfigurationProvider("xwork-sample.xml");
        container.inject(provider);
        configurationManager.addContainerProvider(provider);

        Configuration config = null;
        try {
            config = configurationManager.getConfiguration();
        } catch (ConfigurationException e) {
            e.printStackTrace();
            fail();
        }
        
        RuntimeConfiguration configuration = config.getRuntimeConfiguration();

        // check that it has configuration from xml
        assertNotNull(configuration.getActionConfig("/foo/bar", "Bar"));

        mockContainerProvider.verify();
    }
