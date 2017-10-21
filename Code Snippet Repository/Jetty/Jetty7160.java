    public void prepare(int port)
    {
        server = new Server();
        connector = new ServerConnector(server);
        connector.setPort(port);
        server.addConnector(connector);

        WebSocketHandler wsHandler = new WebSocketHandler()
        {
            @Override
            public void configure(WebSocketServletFactory factory)
            {
                LOG.debug("Configuring WebSocketServerFactory ...");

                // Registering Frame Debug
                factory.getExtensionFactory().register("@frame-capture",FrameCaptureExtension.class);

                // Setup the desired Socket to use for all incoming upgrade requests
                factory.setCreator(BrowserDebugTool.this);

                // Set the timeout
                factory.getPolicy().setIdleTimeout(30000);

                // Set top end message size
                factory.getPolicy().setMaxTextMessageSize(15 * 1024 * 1024);
            }
        };

        server.setHandler(wsHandler);

        String resourceBase = "src/test/resources/browser-debug-tool";

        ResourceHandler rHandler = new ResourceHandler();
        rHandler.setDirectoriesListed(true);
        rHandler.setResourceBase(resourceBase);
        wsHandler.setHandler(rHandler);

        LOG.info("{} setup on port {}",this.getClass().getName(),port);
    }
