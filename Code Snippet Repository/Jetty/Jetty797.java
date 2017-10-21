    @Test
    public void testReceiveApp() throws Exception
    {
        DeploymentManager depman = new DeploymentManager();
        depman.setContexts(new ContextHandlerCollection());
        depman.setDefaultLifeCycleGoal(null); // no default
        AppLifeCyclePathCollector pathtracker = new AppLifeCyclePathCollector();
        MockAppProvider mockProvider = new MockAppProvider();

        depman.addLifeCycleBinding(pathtracker);
        depman.addAppProvider(mockProvider);

        // Start DepMan
        depman.start();

        // Trigger new App
        mockProvider.findWebapp("foo-webapp-1.war");

        // Test app tracking
        Collection<App> apps = depman.getApps();
        Assert.assertNotNull("Should never be null",apps);
        Assert.assertEquals("Expected App Count",1,apps.size());

        // Test app get
        App actual = depman.getAppByOriginId("mock-foo-webapp-1.war");
        Assert.assertNotNull("Should have gotten app (by id)",actual);
        Assert.assertEquals("Should have gotten app (by id)","mock-foo-webapp-1.war",actual.getOriginId());
    }
