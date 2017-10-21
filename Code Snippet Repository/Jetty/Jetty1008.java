    @Override
    protected void customizeContext(ServletContextHandler context)
    {
        QueuedThreadPool serverThreads = (QueuedThreadPool)context.getServer().getThreadPool();
        serverThreads.setDetailedDump(true);
        serverThreads.setMaxThreads(5);
        serverThreads.setLowThreadsThreshold(1);

        AbstractHTTP2ServerConnectionFactory h2 = connector.getBean(AbstractHTTP2ServerConnectionFactory.class);
        h2.setInitialSessionRecvWindow(Integer.MAX_VALUE);
    }
