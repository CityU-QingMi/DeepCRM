    @Test
    public void testLog4J12Fragement() throws BundleException, ReflectiveOperationException {

        final Bundle api = getApiBundle();
        final Bundle core = getCoreBundle();
        final Bundle compat = get12ApiBundle();

        api.start();
        core.start();
        
        final Class<?> coreClassFromCore = core.loadClass("org.apache.logging.log4j.core.Core");
        final Class<?> levelClassFrom12API = core.loadClass("org.apache.log4j.Level");
        final Class<?> levelClassFromAPI = core.loadClass("org.apache.logging.log4j.Level");

        Assert.assertEquals("expected 1.2 API Level to have the same class loader as Core", levelClassFrom12API.getClassLoader(), coreClassFromCore.getClassLoader());
        Assert.assertNotEquals("expected 1.2 API Level NOT to have the same class loader as API Level", levelClassFrom12API.getClassLoader(), levelClassFromAPI.getClassLoader());

        core.stop();
        api.stop();
        
        uninstall(api, core, compat);
    }
