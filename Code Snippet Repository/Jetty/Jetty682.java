    @After
    public void destroy() throws Exception
    {
        if (acceptor != null)
            acceptor.close();
        if (proxy != null)
            proxy.stop();
        if (client != null)
            client.stop();
        if (threadPool != null)
            threadPool.shutdownNow();
    }
