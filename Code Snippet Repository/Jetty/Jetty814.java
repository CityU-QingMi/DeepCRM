    @Test
    public void testAfterStartupContext() throws IOException
    {
        jetty.copyWebapp("foo-webapp-1.war","foo.war");
        jetty.copyWebapp("foo.xml","foo.xml");

        waitForDirectoryScan();
        waitForDirectoryScan();

        jetty.assertWebAppContextsExists("/foo");
    }
