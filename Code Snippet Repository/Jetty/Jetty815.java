    @Test
    public void testAfterStartupThenRemoveContext() throws IOException
    {
        jetty.copyWebapp("foo-webapp-1.war","foo.war");
        jetty.copyWebapp("foo.xml","foo.xml");

        waitForDirectoryScan();
        waitForDirectoryScan();

        jetty.assertWebAppContextsExists("/foo");

        jetty.removeWebapp("foo.war");
        jetty.removeWebapp("foo.xml");

        waitForDirectoryScan();
        waitForDirectoryScan();

        jetty.assertNoWebAppContexts();
    }
