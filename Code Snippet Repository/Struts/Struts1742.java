    public void testObjectFactoryDestroy() throws Exception {

        ConfigurationManager cm = new ConfigurationManager(Container.DEFAULT_NAME);
        Dispatcher du = new MockDispatcher(new MockServletContext(), new HashMap<String, String>(), cm);
        Mock mockConfiguration = new Mock(Configuration.class);
        cm.setConfiguration((Configuration)mockConfiguration.proxy());
        
        Mock mockContainer = new Mock(Container.class);
        String reloadConfigs = container.getInstance(String.class, XWorkConstants.RELOAD_XML_CONFIGURATION);
        mockContainer.expectAndReturn("getInstance", C.args(C.eq(String.class), C.eq(XWorkConstants.RELOAD_XML_CONFIGURATION)),
                reloadConfigs);
        final InnerDestroyableObjectFactory destroyedObjectFactory = new InnerDestroyableObjectFactory((Container) mockContainer.proxy());
        mockContainer.expectAndReturn("getInstance", C.args(C.eq(ObjectFactory.class)), destroyedObjectFactory);

        mockConfiguration.expectAndReturn("getContainer", mockContainer.proxy());
        mockConfiguration.expectAndReturn("getContainer", mockContainer.proxy());
        mockConfiguration.expect("destroy");
        mockConfiguration.matchAndReturn("getPackageConfigs", new HashMap<String, PackageConfig>());

        du.init();
        assertFalse(destroyedObjectFactory.destroyed);
        du.cleanup();
        assertTrue(destroyedObjectFactory.destroyed);
        mockConfiguration.verify();
        mockContainer.verify();
    }
