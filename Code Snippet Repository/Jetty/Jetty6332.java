    @Test(timeout = 2000)
    public void testPongSocket() throws Exception
    {
        EchoClientSocket socket = new EchoClientSocket(1);
        URI toUri = serverUri.resolve("pong-socket");

        try
        {
            // Connect
            client.connectToServer(socket,toUri);
            socket.waitForConnected(1,TimeUnit.SECONDS);

            // Send Ping
            String msg = "hello";
            socket.sendPong(msg);

            // Collect Responses
            socket.awaitAllEvents(1,TimeUnit.SECONDS);
            EventQueue<String> received = socket.eventQueue;

            // Validate Responses
            Assert.assertThat("Received Ping Responses",received,contains("@OnMessage(PongMessage)[/pong-socket]:" + msg));
        }
        finally
        {
            // Close
            socket.close();
        }
    }
