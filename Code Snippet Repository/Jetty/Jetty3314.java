    @Test
    public void testGracefulNoWaiter() throws Exception
    {
        Server server= new Server();
        server.setStopTimeout(1000);

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(0);
        server.addConnector(connector);

        TestHandler handler = new TestHandler();
        server.setHandler(handler);

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
        server.stop();
        long stop = System.nanoTime();
        
        // No Graceful waiters
        assertThat(TimeUnit.NANOSECONDS.toMillis(stop-start),lessThan(900L));

        assertThat(client.getInputStream().read(),Matchers.is(-1));

        assertThat(handler.handling.get(),Matchers.is(false));
        assertThat(handler.thrown.get(),
                Matchers.anyOf(
                instanceOf(ClosedChannelException.class),
                instanceOf(EofException.class),
                instanceOf(EOFException.class))
                );

        client.close();
    }
