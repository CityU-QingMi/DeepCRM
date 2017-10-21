    public static HttpClient newHttpClient(WebSocketContainerScope scope)
    {
        SslContextFactory sslContextFactory = null;
        Executor executor = null;
        
        if (scope != null)
        {
            sslContextFactory = scope.getSslContextFactory();
            executor = scope.getExecutor();
        }
        
        if (sslContextFactory == null)
        {
            sslContextFactory = new SslContextFactory();
        }
        
        HttpClient client = new HttpClient(sslContextFactory);
        if (executor == null)
        {
            QueuedThreadPool threadPool = new QueuedThreadPool();
            String name = "WebSocketClient@" + client.hashCode();
            threadPool.setName(name);
            threadPool.setDaemon(true);
            executor = threadPool;
        }
        client.setExecutor(executor);
        return client;
    }
