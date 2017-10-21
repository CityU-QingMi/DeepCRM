    @Before
    public void prepare() throws Exception
    {
        server = new Server();
        connector = new ServerConnector(server);
        server.addConnector(connector);
    
        servletContextHandler = new ServletContextHandler(server, "/", true, false);
        ServerContainer container = WebSocketServerContainerInitializer.configureContext(servletContextHandler);
        
        ServerEndpointConfig config = ServerEndpointConfig.Builder.create(BasicEchoEndpoint.class, "/").build();
        container.addEndpoint(config);

        client = ContainerProvider.getWebSocketContainer();
        
        server.start();
    }
