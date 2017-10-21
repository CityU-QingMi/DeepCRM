    protected void startProxy() throws Exception
    {
        QueuedThreadPool proxyThreads = new QueuedThreadPool();
        proxyThreads.setName("proxy");
        proxy = new Server(proxyThreads);
        proxyConnector = new ServerConnector(proxy);
        proxy.addConnector(proxyConnector);
        // Under Windows, it takes a while to detect that a connection
        // attempt fails, so use an explicit timeout
        ConnectHandler connectHandler = new ConnectHandler();
        connectHandler.setConnectTimeout(1000);
        proxy.setHandler(connectHandler);

        ServletContextHandler proxyHandler = new ServletContextHandler(connectHandler, "/");
        proxyHandler.addServlet(ProxyServlet.class, "/*");

        proxy.start();
    }
