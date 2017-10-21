    @Test
    public void testSimpleLogInAnOsgiContext() throws BundleException, ReflectiveOperationException {

        final Bundle api = getApiBundle();
        final Bundle core = getCoreBundle();
        final Bundle dummy = getDummyBundle();

        start(api, core, dummy);

        final PrintStream bakStream = System.out;
        try {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final PrintStream logStream = new PrintStream(baos);
            System.setOut(logStream);

            log(dummy);

            final String result = baos.toString().substring(
                12).trim(); // remove the instant then the spaces at start and end, that are non constant
            Assert.assertEquals("[main] ERROR org.apache.logging.log4j.configuration.CustomConfiguration - Test OK",
                result);
        } finally {
            System.setOut(bakStream);
        }

        stop(api, core, dummy);
        uninstall(api, core, dummy);
    }
