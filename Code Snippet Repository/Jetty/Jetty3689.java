    private void setupServer() throws Exception
    {
        // Setup server
        server = new Server();
        localConnector = new LocalConnector(server);
        server.addConnector(localConnector);

        ResourceHandler fileResourceHandler = new ResourceHandler();
        fileResourceHandler.setDirectoriesListed(true);
        fileResourceHandler.setWelcomeFiles(new String[]{"index.html"});
        fileResourceHandler.setEtags(true);

        ContextHandler fileResourceContext = new ContextHandler();
        fileResourceContext.setContextPath("/");
        fileResourceContext.setAllowNullPathInfo(true);
        fileResourceContext.setHandler(fileResourceHandler);
        fileResourceContext.setBaseResource(new PathResource(rootPath));

        fileResourceContext.clearAliasChecks();
        fileResourceContext.addAliasCheck(new AllowSymLinkAliasChecker());

        server.setHandler(fileResourceContext);
        server.start();
    }
