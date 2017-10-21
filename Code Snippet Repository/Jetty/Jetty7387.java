    @BeforeClass
    public static void setUpServer() throws Exception
    {
        server = new TestableJettyServer();
        server.setScheme(HttpScheme.HTTP.asString());
        server.addConfiguration("DefaultHandler.xml");
        server.addConfiguration("NIOHttp.xml");

        server.load();
        server.start();
    }
