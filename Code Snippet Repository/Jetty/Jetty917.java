    public void prepare(HttpServlet servlet) throws Exception
    {
        server = new Server();
        connector = new ServerConnector(server);
        server.addConnector(connector);

        SslContextFactory sslContextFactory = new SslContextFactory();
        sslContextFactory.setEndpointIdentificationAlgorithm("");
        sslContextFactory.setKeyStorePath("src/test/resources/keystore.jks");
        sslContextFactory.setKeyStorePassword("storepwd");
        sslContextFactory.setTrustStorePath("src/test/resources/truststore.jks");
        sslContextFactory.setTrustStorePassword("storepwd");
        sslConnector = new ServerConnector(server, sslContextFactory);
        server.addConnector(sslConnector);

        ServletContextHandler context = new ServletContextHandler(server, "/");

        FilterHolder filterHolder = context.addFilter(TryFilesFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        forwardPath = "/index.php";
        filterHolder.setInitParameter(TryFilesFilter.FILES_INIT_PARAM, "$path " + forwardPath + "?p=$path");

        context.addServlet(new ServletHolder(servlet), "/*");

        client = new HttpClient(sslContextFactory);
        server.addBean(client);

        server.start();
    }
