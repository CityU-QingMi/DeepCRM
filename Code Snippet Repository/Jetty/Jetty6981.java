    @Before
    public void prepare() throws Exception
    {
        server = new Server();
        connector = new ServerConnector(server);
        server.addConnector(connector);

        WebSocketHandler handler = new WebSocketHandler()
        {
            @Override
            public void configure(WebSocketServletFactory factory)
            {
                factory.register(EchoSocket.class);
            }
        };

        server.setHandler(handler);

        client = new WebSocketClient();
        server.addBean(client, true);

        server.start();
    }
