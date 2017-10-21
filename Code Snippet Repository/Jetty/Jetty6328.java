    @BeforeClass
    public static void startServer() throws Exception
    {
        File testdir = MavenTestingUtils.getTargetTestingDir(PingPongTest.class.getName());
        server = new WSServer(testdir,"app");
        server.copyWebInf("pong-config-web.xml");

        server.copyClass(PongContextListener.class);
        server.copyClass(PongMessageEndpoint.class);
        server.copyClass(PongSocket.class);

        server.start();
        serverUri = server.getServerBaseURI();

        WebAppContext webapp = server.createWebAppContext();
        server.deployWebapp(webapp);
    }
