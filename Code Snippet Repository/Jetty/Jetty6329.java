    @Test(timeout = 2000)
    public void testPingEndpoint() throws Exception
    {
        EchoClientSocket socket = new EchoClientSocket(1);
        URI toUri = serverUri.resolve("ping");

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
            Assert.assertThat("Received Ping Response",actual,containsString("PongMessage[/ping]:" + msg));
        }
        finally
        {
            // Close
            socket.close();
        }
    }
