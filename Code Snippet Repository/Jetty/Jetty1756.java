    @After
    public void destroy() throws Exception
    {
        if (scheduler!=null)
            scheduler.stop();
        if (selectorManager != null)
            selectorManager.stop();
        if (connector != null)
            connector.close();
        if (threadPool != null)
            threadPool.stop();
    }
