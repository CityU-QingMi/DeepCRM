    @Override
    protected void doStart() throws Exception
    {
        WebSocketPolicy policy = new WebSocketPolicy(WebSocketBehavior.SERVER);
        configurePolicy(policy);
        webSocketFactory = new WebSocketServerFactory(policy, getServer().getThreadPool(), bufferPool);
        addBean(webSocketFactory);
        configure(webSocketFactory);
        super.doStart();
    }
