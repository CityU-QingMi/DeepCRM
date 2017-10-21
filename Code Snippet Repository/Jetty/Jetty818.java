    @Before
    public void setupEnvironment() throws Exception
    {
        jetty = new XmlConfiguredJetty(testdir);
        jetty.addConfiguration("jetty.xml");
        jetty.addConfiguration("jetty-http.xml");
        jetty.addConfiguration("jetty-deploy-wars.xml");

        // Setup initial context
        jetty.copyWebapp("foo-webapp-1.war","foo.war");
        
        // Make symlink
        Path pathWar3 = MavenTestingUtils.getTestResourcePathFile("webapps/foo-webapp-3.war");
        Path pathBar = jetty.getJettyDir("webapps/bar.war").toPath();
        try
        {
            Files.createSymbolicLink(pathBar, pathWar3);
            symlinkSupported = true;
        } catch (UnsupportedOperationException | FileSystemException e)
        {
            // if unable to create symlink, no point testing that feature
            // this is the path that Microsoft Windows takes.
            symlinkSupported = false;
        }

        // Should not throw an Exception
        jetty.load();

        // Start it
        jetty.start();
    }
