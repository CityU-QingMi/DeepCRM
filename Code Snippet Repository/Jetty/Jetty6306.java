    @BeforeClass
    public static void startServer() throws Exception
    {
        File testdir = MavenTestingUtils.getTargetTestingDir(EchoTest.class.getName());
        server = new WSServer(testdir,"app");
        server.copyWebInf("empty-web.xml");

        for (EchoCase cases[] : TESTCASES)
        {
            for (EchoCase ecase : cases)
            {
                server.copyClass(ecase.serverPojo);
            }
        }

        server.start();
        serverUri = server.getServerBaseURI();

        WebAppContext webapp = server.createWebAppContext();
        server.deployWebapp(webapp);
    }
