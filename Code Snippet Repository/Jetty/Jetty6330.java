    @Test(timeout = 2000)
    public void testPongEndpoint() throws Exception
    {
        EchoClientSocket socket = new EchoClientSocket(1);
        URI toUri = serverUri.resolve("pong");

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
            Assert.assertThat("Received Ping Responses",received,contains("PongMessage[/pong]:" + msg));
        }
        finally
        {
            // Close
            socket.close();
        }
    }
