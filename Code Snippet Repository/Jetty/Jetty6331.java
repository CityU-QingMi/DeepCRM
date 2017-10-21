    @Test(timeout = 2000)
    public void testPingSocket() throws Exception
    {
        EchoClientSocket socket = new EchoClientSocket(1);
        URI toUri = serverUri.resolve("ping-socket");

        try
        {
            // Connect
            client.connectToServer(socket,toUri);
            socket.waitForConnected(1,TimeUnit.SECONDS);

            // Send Ping
            String msg = "hello";
            socket.sendPing(msg);

            // Collect Responses
            socket.awaitAllEvents(1,TimeUnit.SECONDS);
            EventQueue<String> received = socket.eventQueue;

            // Validate Responses
            String actual = received.poll();
            Assert.assertThat("Received Ping Response",actual,containsString("@OnMessage(PongMessage)[/ping-socket]:" + msg));
        }
        finally
        {
            // Close
            socket.close();
        }
    }
