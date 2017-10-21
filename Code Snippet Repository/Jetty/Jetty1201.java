    private void startClient() throws Exception
    {
        QueuedThreadPool clientThreads = new QueuedThreadPool();
        clientThreads.setName("client");
        HttpClientTransportOverHTTP2 transport = new HttpClientTransportOverHTTP2(new HTTP2Client());
        transport.setUseALPN(false);
        client = new HttpClient(transport, newSslContextFactory());
        client.setExecutor(clientThreads);
        client.start();
    }
