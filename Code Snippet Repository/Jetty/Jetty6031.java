    @Test
    public void testNullSessionAndSecurityHandler() throws Exception
    {
        Server server = new Server(0);
        HandlerList handlers = new HandlerList();
        ContextHandlerCollection contexts = new ContextHandlerCollection();
        WebAppContext context = new WebAppContext(null, null, null, null, null, new ErrorPageErrorHandler(), 
                                                  ServletContextHandler.NO_SESSIONS|ServletContextHandler.NO_SECURITY);
        context.setContextPath("/");
        context.setBaseResource(Resource.newResource("./src/test/webapp"));
        server.setHandler(handlers);
        handlers.addHandler(contexts);
        contexts.addHandler(context);

        LocalConnector connector = new LocalConnector(server);
        server.addConnector(connector);

        try
        {
            server.start();
            Assert.assertTrue(context.isAvailable());
        }
        finally
        {
            server.stop();
        }
    }
