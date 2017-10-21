    public WebSocketUpgradeRequest(WebSocketClient wsClient, HttpClient httpClient, URI wsURI, Object localEndpoint)
    {
        super(httpClient,new HttpConversation(),wsURI);
        
        apiRequestFacade = new ClientUpgradeRequestFacade();

        if (!wsURI.isAbsolute())
        {
            throw new IllegalArgumentException("WebSocket URI must be an absolute URI: " + wsURI);
        }

        String scheme = wsURI.getScheme();
        if (scheme == null || !(scheme.equalsIgnoreCase("ws") || scheme.equalsIgnoreCase("wss")))
        {
            throw new IllegalArgumentException("WebSocket URI must use 'ws' or 'wss' scheme: " + wsURI);
        }

        this.wsClient = wsClient;
        try
        {
            if (!this.wsClient.isRunning())
            {
                this.wsClient.start();
            }
        }
        catch (Exception e)
        {
            throw new IllegalStateException("Unable to start WebSocketClient", e);
        }
        this.localEndpoint = this.wsClient.getEventDriverFactory().wrap(localEndpoint);

        this.fut = new CompletableFuture<Session>();
    }
