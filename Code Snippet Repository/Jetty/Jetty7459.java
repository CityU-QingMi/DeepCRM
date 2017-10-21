    @BeforeClass
    public static void setupServer() throws Exception
    {
        TestableJettyServer server = new TestableJettyServer();
        server.setScheme(HttpScheme.HTTPS.asString());
        server.addConfiguration("RFC2616Base.xml");
        server.addConfiguration("RFC2616_Redirects.xml");
        server.addConfiguration("RFC2616_Filters.xml");
        server.addConfiguration("NIOHttps.xml");
        setUpServer(server, RFC2616NIOHttpsTest.class);
    }
