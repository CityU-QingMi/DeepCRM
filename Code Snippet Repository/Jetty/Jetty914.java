    public void prepare(HttpServlet servlet) throws Exception
    {
        QueuedThreadPool serverThreads = new QueuedThreadPool();
        serverThreads.setName("server");
        server = new Server(serverThreads);
        httpConnector = new ServerConnector(server);
        server.addConnector(httpConnector);

        fcgiConnector = new ServerConnector(server, new ServerFCGIConnectionFactory(new HttpConfiguration(), sendStatus200));
        server.addConnector(fcgiConnector);

        final String contextPath = "/";
        context = new ServletContextHandler(server, contextPath);

        final String servletPath = "/script";
        FastCGIProxyServlet fcgiServlet = new FastCGIProxyServlet()
        {
            @Override
            protected String rewriteTarget(HttpServletRequest request)
            {
                return "http://localhost:" + fcgiConnector.getLocalPort() + servletPath + request.getServletPath();
            }
        };
        ServletHolder fcgiServletHolder = new ServletHolder(fcgiServlet);
        fcgiServletHolder.setName("fcgi");
        fcgiServletHolder.setInitParameter(FastCGIProxyServlet.SCRIPT_ROOT_INIT_PARAM, "/scriptRoot");
        fcgiServletHolder.setInitParameter("proxyTo", "http://localhost");
        fcgiServletHolder.setInitParameter(FastCGIProxyServlet.SCRIPT_PATTERN_INIT_PARAM, "(.+?\\.php)");
        context.addServlet(fcgiServletHolder, "*.php");

        context.addServlet(new ServletHolder(servlet), servletPath + "/*");

        QueuedThreadPool clientThreads = new QueuedThreadPool();
        clientThreads.setName("client");
        client = new HttpClient();
        client.setExecutor(clientThreads);
        server.addBean(client);

        server.start();
    }
