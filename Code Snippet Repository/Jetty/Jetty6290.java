    @Test
    public void testHttpClientThreads_AfterServerConfigure() throws Exception
    {
        Server server = new Server(0);
        ServletContextHandler contextHandler = new ServletContextHandler();
        server.setHandler(contextHandler);
        // Using JSR356 Server Techniques to configure WebSocketContainer
        contextHandler.addServlet(ServerConfigureServlet.class, "/configure");
        javax.websocket.server.ServerContainer container = WebSocketServerContainerInitializer.configureContext(contextHandler);
        container.addEndpoint(EchoSocket.class);
        try
        {
            server.start();
            String response = GET(server.getURI().resolve("/configure"));
            assertThat("Response", response, startsWith("Configured " + ServerContainer.class.getName()));
            List<String> threadNames = getThreadNames((ContainerLifeCycle)container, server);
            assertNoHttpClientPoolThreads(threadNames);
            assertThat("Threads", threadNames, not(hasItem(containsString("WebSocketContainer@"))));
        }
        finally
        {
            server.stop();
        }
    }
