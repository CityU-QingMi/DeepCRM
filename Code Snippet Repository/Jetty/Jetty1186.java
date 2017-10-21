    @Override
    protected void doStart() throws Exception
    {
        if (!client.isStarted())
        {
            HttpClient httpClient = getHttpClient();
            client.setExecutor(httpClient.getExecutor());
            client.setScheduler(httpClient.getScheduler());
            client.setByteBufferPool(httpClient.getByteBufferPool());
            client.setConnectTimeout(httpClient.getConnectTimeout());
            client.setIdleTimeout(httpClient.getIdleTimeout());
            client.setInputBufferSize(httpClient.getResponseBufferSize());
        }
        addBean(client);
        super.doStart();

        this.connectionFactory = new HTTP2ClientConnectionFactory();
        client.setClientConnectionFactory((endPoint, context) ->
        {
            HttpDestination destination = (HttpDestination)context.get(HTTP_DESTINATION_CONTEXT_KEY);
            return destination.getClientConnectionFactory().newConnection(endPoint, context);
        });
    }
