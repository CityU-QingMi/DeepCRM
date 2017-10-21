    @Test
    public void testReuseAddress_False() throws Exception
    {
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(0);
        connector.setReuseAddress(false);
        server.addConnector(connector);

        HandlerList handlers = new HandlerList();
        handlers.addHandler(new ReuseInfoHandler());
        handlers.addHandler(new DefaultHandler());

        server.setHandler(handlers);

        try
        {
            server.start();

            URI uri = toServerURI(connector);
            String response = getResponse(uri);
            assertThat("Response",response,containsString("connector.getReuseAddress() = false"));
            assertThat("Response",response,containsString("connector._reuseAddress() = false"));

            // Java on Windows is incapable of propagating reuse-address this to the opened socket.
            if (!OS.IS_WINDOWS)
            {
                assertThat("Response",response,containsString("socket.getReuseAddress() = false"));
            }
        }
        finally
        {
            server.stop();
        }
    }
