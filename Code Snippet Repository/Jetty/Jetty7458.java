    @BeforeClass
    public static void setupServer() throws Exception
    {
        TestableJettyServer server = new TestableJettyServer();
        server.setScheme(HttpScheme.HTTP.asString());
        server.addConfiguration("RFC2616Base.xml");
        server.addConfiguration("RFC2616_Redirects.xml");
        server.addConfiguration("RFC2616_Filters.xml");
        server.addConfiguration("NIOHttp.xml");
        setUpServer(server, RFC2616NIOHttpTest.class);
    }
