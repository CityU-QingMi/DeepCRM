    @After
    public void destroy() throws Exception
    {
        if (proxy != null)
            proxy.stop();
        if (server != null)
            server.stop();
        if (threadPool != null)
            threadPool.shutdownNow();
    }
