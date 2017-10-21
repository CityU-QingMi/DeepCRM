    @BeforeClass
    public static void startServer() throws Exception
    {
        server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(0);
        server.addConnector(connector);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        ServerContainer wsContainer = WebSocketServerContainerInitializer.configureContext(context);

        // Prepare Server Side Output directory for uploaded files
        outputDir = MavenTestingUtils.getTargetTestingDir(StreamTest.class.getName());
        FS.ensureEmpty(outputDir);

        // Create Server Endpoint with output directory configuration
        ServerEndpointConfig config = ServerEndpointConfig.Builder.create(UploadSocket.class,"/upload/{filename}")
                .configurator(new ServerUploadConfigurator(outputDir)).build();
        wsContainer.addEndpoint(config);

        server.start();

        String host = connector.getHost();
        if (host == null)
        {
            host = "localhost";
        }
        int port = connector.getLocalPort();
        serverUri = new URI(String.format("ws://%s:%d/",host,port));
        if (LOG.isDebugEnabled())
            LOG.debug("Server started on {}",serverUri);
    }
