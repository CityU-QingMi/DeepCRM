    private WebSocketClient(SslContextFactory sslContextFactory, Executor executor, ByteBufferPool bufferPool, DecoratedObjectFactory objectFactory)
    {
        this.httpClient = new HttpClient(sslContextFactory);
        this.httpClient.setExecutor(executor);
        this.httpClient.setByteBufferPool(bufferPool);
        addBean(this.httpClient);
        
        this.containerScope = new SimpleContainerScope(WebSocketPolicy.newClientPolicy(), bufferPool, objectFactory);

        this.extensionRegistry = new WebSocketExtensionFactory(containerScope);

        this.eventDriverFactory = new EventDriverFactory(containerScope);
        this.sessionFactory = new WebSocketSessionFactory(containerScope);
    }
