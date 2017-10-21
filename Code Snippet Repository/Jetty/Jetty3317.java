    @Test
    public void testHalfCloseRace() throws Exception
    {
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server,1,1);
        connector.setPort(0);
        connector.setIdleTimeout(500);
        server.addConnector(connector);
        TestHandler handler = new TestHandler();
        server.setHandler(handler);

        server.start();
        
        try(Socket client = new Socket("localhost",connector.getLocalPort());)
        {
            int in = client.getInputStream().read();
            assertEquals(-1,in);

            client.getOutputStream().write("GET / HTTP/1.0\r\n\r\n".getBytes());

            Thread.sleep(200);
            assertEquals(0,handler.getHandled());
        }
        
    }
