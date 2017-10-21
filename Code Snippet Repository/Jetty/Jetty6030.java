    @Test
    public void testNullPath() throws Exception
    {
        Server server = new Server(0);
        HandlerList handlers = new HandlerList();
        ContextHandlerCollection contexts = new ContextHandlerCollection();
        WebAppContext context = new WebAppContext();
        context.setBaseResource(Resource.newResource("./src/test/webapp"));
        context.setContextPath("/");
        server.setHandler(handlers);
        handlers.addHandler(contexts);
        contexts.addHandler(context);
        
        LocalConnector connector = new LocalConnector(server);
        server.addConnector(connector);
        
        server.start();
        try
        {
            String response = connector.getResponse("GET http://localhost:8080 HTTP/1.1\r\nHost: localhost:8080\r\nConnection: close\r\n\r\n");
            Assert.assertTrue(response.indexOf("200 OK")>=0);
        }
        finally
        {
            server.stop();
        }
    }
