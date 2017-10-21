    @Test
    public void testClassNotFoundErrorLogger() throws BundleException {

        final Bundle api = getApiBundle();
        final Bundle core = getCoreBundle();

        api.start();
        // fails if LOG4J2-1637 is not fixed
        try {
            core.start();
        }
        catch (final BundleException ex) {
            boolean shouldRethrow = true;
            final Throwable t = ex.getCause();
            if (t != null) {
                final Throwable t2 = t.getCause();
                if (t2 != null) {
                    final String cause = t2.toString();
                    final boolean result = cause.equals("java.lang.ClassNotFoundException: org.apache.logging.log4j.Logger") // Equinox
                                  || cause.equals("java.lang.ClassNotFoundException: org.apache.logging.log4j.Logger not found by org.apache.logging.log4j.core [2]"); // Felix
                    Assert.assertFalse("org.apache.logging.log4j package is not properly imported in org.apache.logging.log4j.core bundle, check that the package is exported from api and is not split between api and core", result);
                    shouldRethrow = !result;
                }
            }
            if (shouldRethrow) {
                throw ex; // rethrow if the cause of the exception is something else
            }
        }

        core.stop();
        api.stop();
        
        core.uninstall();
        api.uninstall();
    }
