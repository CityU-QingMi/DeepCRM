    @Before
    public void startServer() throws Exception
    {
        server = new WSServer(MavenTestingUtils.getTargetTestingDir(SessionTest.class.getSimpleName() + "-" + ID.incrementAndGet()),"app");
        server.copyWebInf("empty-web.xml");
        server.copyClass(SessionInfoSocket.class);
        server.copyClass(SessionAltConfig.class);
        server.start();
        serverUri = server.getServerBaseURI();

        WebAppContext webapp = server.createWebAppContext();
        testcase.customize(webapp);
        server.deployWebapp(webapp);
    }
