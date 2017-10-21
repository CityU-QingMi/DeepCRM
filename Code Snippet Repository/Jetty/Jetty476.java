    @Before
    public void setUp() throws Exception
    {
        QueuedThreadPool serverThreads = new QueuedThreadPool();
        serverThreads.setName("server");
        server = new Server(serverThreads);

        SslContextFactory serverSslContextFactory = new SslContextFactory();
        serverSslContextFactory.setKeyStorePath("src/test/resources/keystore.jks");
        serverSslContextFactory.setKeyStorePassword("storepwd");
        connector = new ServerConnector(server, serverSslContextFactory);
        server.addConnector(connector);
        server.setHandler(new DefaultHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                response.getWriter().write("foobar");
            }
        });
        server.start();

        // keystore contains a hostname which doesn't match localhost
        clientSslContextFactory.setKeyStorePath("src/test/resources/keystore.jks");
        clientSslContextFactory.setKeyStorePassword("storepwd");

        QueuedThreadPool clientThreads = new QueuedThreadPool();
        clientThreads.setName("client");
        client = new HttpClient(clientSslContextFactory);
        client.setExecutor(clientThreads);
        client.start();
    }
