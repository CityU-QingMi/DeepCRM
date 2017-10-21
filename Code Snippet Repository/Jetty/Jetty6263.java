    @BeforeClass
    public static void startServer() throws Exception
    {
        File testdir = MavenTestingUtils.getTargetTestingDir(AnnotatedServerEndpointTest.class.getName());
        server = new WSServer(testdir,"app");
        server.createWebInf();
        server.copyEndpoint(ConfiguredEchoSocket.class);
        server.copyClass(EchoSocketConfigurator.class);
        server.copyClass(DateDecoder.class);
        server.copyClass(TimeEncoder.class);

        server.start();

        WebAppContext webapp = server.createWebAppContext();
        server.deployWebapp(webapp);
    }
