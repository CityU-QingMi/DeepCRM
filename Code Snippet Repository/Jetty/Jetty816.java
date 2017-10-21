    @Test
    public void testAfterStartupThenUpdateContext() throws Exception
    {
        // This test will not work on Windows as second war file would
        // not be written over the first one because of a file lock
        Assume.assumeTrue(!OS.IS_WINDOWS);
        Assume.assumeTrue(!OS.IS_OSX); // build server has issues with finding itself apparently


        jetty.copyWebapp("foo-webapp-1.war","foo.war");
        jetty.copyWebapp("foo.xml","foo.xml");

        waitForDirectoryScan();
        waitForDirectoryScan();

        jetty.assertWebAppContextsExists("/foo");

        // Test that webapp response contains "-1"
        jetty.assertResponseContains("/foo/info","FooServlet-1");

        waitForDirectoryScan();
        //System.err.println("Updating war files");
        jetty.copyWebapp("foo.xml","foo.xml"); // essentially "touch" the context xml
        jetty.copyWebapp("foo-webapp-2.war","foo.war");

        // This should result in the existing foo.war being replaced with the new foo.war
        waitForDirectoryScan();
        waitForDirectoryScan();
        jetty.assertWebAppContextsExists("/foo");

        // Test that webapp response contains "-2"
        jetty.assertResponseContains("/foo/info","FooServlet-2");
    }
