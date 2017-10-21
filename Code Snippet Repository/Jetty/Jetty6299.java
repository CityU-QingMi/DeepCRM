    @Test
    public void testHttpClientThreads_AfterClientConnectTo() throws Exception
    {
        Server server = new Server(0);
        ServletContextHandler contextHandler = new ServletContextHandler();
        server.setHandler(contextHandler);
        // Using JSR356 Client Techniques to connectToServer()
        contextHandler.addServlet(ClientConnectServlet.class, "/connect");
        javax.websocket.server.ServerContainer container = WebSocketServerContainerInitializer.configureContext(contextHandler);
        container.addEndpoint(EchoSocket.class);
        try
        {
            server.start();
            String response = GET(server.getURI().resolve("/connect"));
            assertThat("Response", response, startsWith("Connected to ws://"));
            List<String> threadNames = getThreadNames(server);
            assertNoHttpClientPoolThreads(threadNames);
            assertThat("Threads", threadNames, hasItem(containsString("WebSocketContainer@")));
        }
        finally
        {
            server.stop();
        }
    }
