    private HttpClient prepareClient() throws Exception
    {
        HttpClient result = new HttpClient();
        QueuedThreadPool executor = new QueuedThreadPool();
        executor.setName("client");
        result.setExecutor(executor);
        result.getProxyConfiguration().getProxies().add(new HttpProxy("localhost", proxyConnector.getLocalPort()));
        result.start();
        return result;
    }
