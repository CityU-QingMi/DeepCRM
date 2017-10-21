    @Test
    public void testHttpClientThreads_AfterServerConnectTo() throws Exception
    {
        Server server = new Server(0);
        ServletContextHandler contextHandler = new ServletContextHandler();
        server.setHandler(contextHandler);
        // Using JSR356 Server Techniques to connectToServer()
        contextHandler.addServlet(ServerConnectServlet.class, "/connect");
        javax.websocket.server.ServerContainer container = WebSocketServerContainerInitializer.configureContext(contextHandler);
        container.addEndpoint(EchoSocket.class);
        try
        {
            server.start();
            String response = GET(server.getURI().resolve("/connect"));
            assertThat("Response", response, startsWith("Connected to ws://"));
            List<String> threadNames = getThreadNames((ContainerLifeCycle)container, server);
            assertNoHttpClientPoolThreads(threadNames);
            assertThat("Threads", threadNames, hasItem(containsString("WebSocketClient@")));
        }
        finally
        {
            server.stop();
        }
    }
