    @Test
    public void testBinding()
    {
        AppLifeCyclePathCollector pathtracker = new AppLifeCyclePathCollector();
        DeploymentManager depman = new DeploymentManager();
        depman.addLifeCycleBinding(pathtracker);

        Set<AppLifeCycle.Binding> allbindings = depman.getLifeCycle().getBindings();
        Assert.assertNotNull("All Bindings should never be null",allbindings);
        Assert.assertEquals("All Bindings.size",1,allbindings.size());

        Set<AppLifeCycle.Binding> deploybindings = depman.getLifeCycle().getBindings("deploying");
        Assert.assertNotNull("'deploying' Bindings should not be null",deploybindings);
        Assert.assertEquals("'deploying' Bindings.size",1,deploybindings.size());
    }
