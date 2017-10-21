    @Before
    public void setupEnvironment() throws Exception
    {
        jetty = new XmlConfiguredJetty(testdir);
        jetty.addConfiguration("jetty.xml");
        jetty.addConfiguration("jetty-http.xml");

        // Setup initial context
        jetty.copyWebapp("foo.xml","foo.xml");
        jetty.copyWebapp("foo-webapp-1.war","foo.war");

    }
