    @BeforeClass
    public static void startServer() throws Exception
    {
        server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(0);
        server.addConnector(connector);

        ContextHandlerCollection contexts = new ContextHandlerCollection();

        File dir = MavenTestingUtils.getTargetTestingDir(ResourceHandlerRangeTest.class.getSimpleName());
        FS.ensureEmpty(dir);
        File rangeFile = new File(dir,"range.txt");
        try (FileWriter writer = new FileWriter(rangeFile))
        {
            writer.append("0123456789");
            writer.flush();
        }

        ContextHandler contextHandler = new ContextHandler();
        ResourceHandler contentResourceHandler = new ResourceHandler();
        contextHandler.setBaseResource(Resource.newResource(dir.getAbsolutePath()));
        contextHandler.setHandler(contentResourceHandler);
        contextHandler.setContextPath("/");

        contexts.addHandler(contextHandler);

        server.setHandler(contexts);
        server.start();

        String host = connector.getHost();
        if (host == null)
        {
            host = "localhost";
        }
        int port = connector.getLocalPort();
        serverUri = new URI(String.format("http://%s:%d/",host,port));
    }
