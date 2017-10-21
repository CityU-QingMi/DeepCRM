    @Test
    public void testGracefulTimeout() throws Exception
    {
        Server server= new Server();
        server.setStopTimeout(1000);

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(0);
        server.addConnector(connector);

        TestHandler handler = new TestHandler();
        StatisticsHandler stats = new StatisticsHandler();
        server.setHandler(stats);
        stats.setHandler(handler);

        server.start();
        final int port=connector.getLocalPort();
        Socket client = new Socket("127.0.0.1", port);
        client.getOutputStream().write((
                "POST / HTTP/1.0\r\n"+
                        "Host: localhost:"+port+"\r\n" +
                        "Content-Type: plain/text\r\n" +
                        "Content-Length: 10\r\n" +
                        "\r\n"+
                        "12345"
                ).getBytes());
        client.getOutputStream().flush();
        handler.latch.await();

        long start = System.nanoTime();
        try
        {
            server.stop();
            Assert.fail();
        }
        catch(TimeoutException e)
        {
            long stop = System.nanoTime();
            // No Graceful waiters
            assertThat(TimeUnit.NANOSECONDS.toMillis(stop-start),greaterThan(900L));
        }

        assertThat(client.getInputStream().read(),Matchers.is(-1));

        assertThat(handler.handling.get(),Matchers.is(false));
        assertThat(handler.thrown.get(),instanceOf(ClosedChannelException.class));

        client.close();
    }
