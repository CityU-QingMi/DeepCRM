    @Test
    public void testApiCoreStartStopStartStop() throws BundleException, ReflectiveOperationException {

        final Bundle api = getApiBundle();
        final Bundle core = getCoreBundle();
        
        Assert.assertEquals("api is not in INSTALLED state", Bundle.INSTALLED, api.getState());
        Assert.assertEquals("core is not in INSTALLED state", Bundle.INSTALLED, core.getState());

        api.start();
        core.start();
        
        Assert.assertEquals("api is not in ACTIVE state", Bundle.ACTIVE, api.getState());        
        Assert.assertEquals("core is not in ACTIVE state", Bundle.ACTIVE, core.getState());        
        
        core.stop();
        api.stop();
        
        Assert.assertEquals("api is not in RESOLVED state", Bundle.RESOLVED, api.getState());
        Assert.assertEquals("core is not in RESOLVED state", Bundle.RESOLVED, core.getState());
        
        api.start();
        core.start();
        
        Assert.assertEquals("api is not in ACTIVE state", Bundle.ACTIVE, api.getState());        
        Assert.assertEquals("core is not in ACTIVE state", Bundle.ACTIVE, core.getState());        
        
        core.stop();
        api.stop();
        
        Assert.assertEquals("api is not in RESOLVED state", Bundle.RESOLVED, api.getState());
        Assert.assertEquals("core is not in RESOLVED state", Bundle.RESOLVED, core.getState());
        
        core.uninstall();
        api.uninstall();
        
        Assert.assertEquals("api is not in UNINSTALLED state", Bundle.UNINSTALLED, api.getState());
        Assert.assertEquals("core is not in UNINSTALLED state", Bundle.UNINSTALLED, core.getState());
    }
