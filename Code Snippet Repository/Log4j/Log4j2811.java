    @Test
    public void testMissingImportOfCoreOsgiPackage() throws BundleException, ReflectiveOperationException {

        final Bundle api = getApiBundle();
        final Bundle core = getCoreBundle();
        final Bundle dummy = getDummyBundle();

        start(api, core, dummy);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final PrintStream logStream = new PrintStream(baos);

        final PrintStream bakStream = setupStream(api, logStream);

        log(dummy);

        setupStream(api, bakStream);

        final boolean result = baos.toString().contains(
            "ERROR StatusLogger Unable to create context org.apache.logging.log4j.core.osgi.BundleContextSelector");
        Assert.assertFalse(
            "org.apache.logging.log4j.core.osgi;resolution:=optional is missing in Import-Package in the POM", result);

        stop(api, core, dummy);
        uninstall(api, core, dummy);
    }
