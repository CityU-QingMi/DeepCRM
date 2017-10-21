    public HttpClientTransportOverFCGI(int selectors, boolean multiplexed, String scriptRoot)
    {
        super(selectors);
        this.multiplexed = multiplexed;
        this.scriptRoot = scriptRoot;
        setConnectionPoolFactory(destination ->
        {
            HttpClient httpClient = getHttpClient();
            int maxConnections = httpClient.getMaxConnectionsPerDestination();
            return isMultiplexed() ?
                    new MultiplexConnectionPool(destination, maxConnections, destination, httpClient.getMaxRequestsQueuedPerDestination()) :
                    new DuplexConnectionPool(destination, maxConnections, destination);
        });
    }
