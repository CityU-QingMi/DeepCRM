    public HTTP2CServer(int port)
    {
        HttpConfiguration config = new HttpConfiguration();
        // HTTP + HTTP/2 connector
        
        HttpConnectionFactory http1 = new HttpConnectionFactory(config);
        HTTP2CServerConnectionFactory http2c = new HTTP2CServerConnectionFactory(config);
        ServerConnector connector = new ServerConnector(this,http1,http2c);
        connector.setPort(port);
        addConnector(connector);

        ((QueuedThreadPool)getThreadPool()).setName("server");

        setHandler(new SimpleHandler());
        
    }
