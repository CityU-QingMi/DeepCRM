    @Before
    public void prepare() throws Exception
    {
        server = new Server();
        connector = new ServerConnector(server);
        server.addConnector(connector);

        ServletContextHandler context = new ServletContextHandler(server, "/", true, false);
        ServerContainer container = WebSocketServerContainerInitializer.configureContext(context);
        ServerEndpointConfig config = ServerEndpointConfig.Builder.create(ServerBinaryStreamer.class, PATH).build();
        container.addEndpoint(config);

        server.start();

        wsClient = ContainerProvider.getWebSocketContainer();
    }
