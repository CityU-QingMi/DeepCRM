    @Test
    public void testNoExtraHttpClientThreads() throws Exception
    {
        Server server = new Server(0);
        ServletContextHandler contextHandler = new ServletContextHandler();
        server.setHandler(contextHandler);
        try
        {
            server.start();
            List<String> threadNames = getThreadNames(server);
            assertNoHttpClientPoolThreads(threadNames);
            assertThat("Threads", threadNames, not(hasItem(containsString("WebSocketContainer@"))));
            assertThat("Threads", threadNames, not(hasItem(containsString("WebSocketClient@"))));
        }
        finally
        {
            server.stop();
        }
    }
