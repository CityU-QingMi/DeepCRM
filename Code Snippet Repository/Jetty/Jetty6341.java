    @Before
    public void startServer() throws Exception
    {
        QueuedThreadPool serverThreads = new QueuedThreadPool();
        serverThreads.setName("server");
        server = new Server(serverThreads);
        ServerConnector serverConnector = new ServerConnector(server);
        serverConnector.setPort(0);
        server.addConnector(serverConnector);
        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        servletContextHandler.setContextPath("/");
        server.setHandler(servletContextHandler);

        serverContainer = WebSocketServerContainerInitializer.configureContext(servletContextHandler);
        serverContainer.addEndpoint(EchoSocket.class);

        wsServerFactory = serverContainer.getWebSocketServerFactory();

        server.start();

        serverURI = new URI("ws://localhost:" + serverConnector.getLocalPort());
    }
