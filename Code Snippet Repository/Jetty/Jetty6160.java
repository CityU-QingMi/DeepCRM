    @BeforeClass
    public static void startEnv() throws Exception
    {
        // Server

        server = new Server();
        ServerConnector connector = new ServerConnector(server);
        server.addConnector(connector);

        handler = new EchoHandler();

        ContextHandler context = new ContextHandler();
        context.setContextPath("/");
        context.setHandler(handler);
        server.setHandler(context);

        // Start Server
        server.start();

        String host = connector.getHost();
        if (host == null)
        {
            host = "localhost";
        }
        int port = connector.getLocalPort();
        serverUri = new URI(String.format("ws://%s:%d/",host,port));

        // Connect client

        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        server.addBean(container); // allow to shutdown with server
        socket = new AnnotatedEndpointClient();

        session = container.connectToServer(socket,serverUri);
        Assert.assertThat("Session",session,notNullValue());

        config = socket.config;
        Assert.assertThat("EndpointConfig",config,notNullValue());
        Assert.assertThat("EndpointConfig",config,instanceOf(ClientEndpointConfig.class));

        ceconfig = (ClientEndpointConfig)config;
        Assert.assertThat("EndpointConfig",ceconfig,notNullValue());
    }
