    @Test
    public void testHttpClientThreads_AfterClientConfigure() throws Exception
    {
        Server server = new Server(0);
        ServletContextHandler contextHandler = new ServletContextHandler();
        server.setHandler(contextHandler);
        // Using JSR356 Client Techniques to configure WebSocketContainer
        contextHandler.addServlet(ClientConfigureServlet.class, "/configure");
        javax.websocket.server.ServerContainer container = WebSocketServerContainerInitializer.configureContext(contextHandler);
        container.addEndpoint(EchoSocket.class);
        try
        {
            server.start();
            String response = GET(server.getURI().resolve("/configure"));
            assertThat("Response", response, startsWith("Configured " + ClientContainer.class.getName()));
            List<String> threadNames = getThreadNames((ContainerLifeCycle)container, server);
            assertNoHttpClientPoolThreads(threadNames);
            assertThat("Threads", threadNames, not(hasItem(containsString("WebSocketContainer@"))));
            assertThat("Threads", threadNames, not(hasItem(containsString("WebSocketClient@"))));
        }
        finally
        {
            server.stop();
        }
    }
