    @Test
    public void testCompleteClose() throws Exception
    {
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server,1,1);
        connector.setPort(0);
        connector.setIdleTimeout(5000);
        final AtomicInteger opened = new AtomicInteger(0);
        final CountDownLatch closed = new CountDownLatch(1);
        connector.addBean(new Connection.Listener()
        {
            @Override
            public void onOpened(Connection connection)
            {
                opened.incrementAndGet();
            }

            @Override
            public void onClosed(Connection connection)
            {
                closed.countDown();
            }
            
        });
        server.addConnector(connector);
        TestHandler handler = new TestHandler();
        server.setHandler(handler);
        
        server.start();
        
        try(Socket client = new Socket("localhost",connector.getLocalPort());)
        {
            client.getOutputStream().write("GET / HTTP/1.0\r\n\r\n".getBytes());
            IO.toString(client.getInputStream());
            assertEquals(1,handler.getHandled());
            assertEquals(1,opened.get());
        }
        assertEquals(true,closed.await(1,TimeUnit.SECONDS));
    }
