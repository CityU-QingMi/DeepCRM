    @BeforeClass
    public static void startServer() throws Exception
    {
        JettyLogHandler.config();

        server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(0);
        server.addConnector(connector);

        EmbeddedCdiHandler context = new EmbeddedCdiHandler();

        File baseDir = MavenTestingUtils.getTestResourcesDir();

        context.setBaseResource(Resource.newResource(baseDir));
        context.setContextPath("/");
        server.setHandler(context);
        
        // Add some websockets
        ServerContainer container = WebSocketServerContainerInitializer.configureContext(context);
        container.addEndpoint(EchoSocket.class);
        container.addEndpoint(InfoSocket.class);

        server.start();

        String host = connector.getHost();
        if (host == null)
        {
            host = "localhost";
        }
        int port = connector.getLocalPort();
        serverHttpURI = new URI(String.format("http://%s:%d/",host,port));
        serverWebsocketURI = new URI(String.format("ws://%s:%d/",host,port));
    }
