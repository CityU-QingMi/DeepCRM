    private void prepareProxy(Map<String, String> initParams) throws Exception
    {
        QueuedThreadPool executor = new QueuedThreadPool();
        executor.setName("proxy");
        proxy = new Server(executor);
        proxyConnector = new ServerConnector(proxy);
        proxy.addConnector(proxyConnector);
        proxyConnector.getConnectionFactory(HttpConnectionFactory.class).getHttpConfiguration().setDelayDispatchUntilContent(false);

        ServletContextHandler proxyCtx = new ServletContextHandler(proxy, "/", true, false);
        ServletHolder proxyServletHolder = new ServletHolder(proxyServlet);
        proxyServletHolder.setInitParameters(initParams);
        proxyCtx.addServlet(proxyServletHolder, "/*");

        proxy.start();

        client = prepareClient();
    }
