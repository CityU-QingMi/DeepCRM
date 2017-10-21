    @OnOpen
    public void onOpen(Session session, EndpointConfig config)
    {
        this.session = session;
        this.config = config;
        if (config instanceof ServerEndpointConfig)
        {
            this.serverConfig = (ServerEndpointConfig)config;
        }
    }
