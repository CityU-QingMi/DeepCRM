    @Test
    public void testWebSocketEcho() throws Exception
    {
        WebSocketClient client = new WebSocketClient();
        try
        {
            client.start();
            CheckSocket socket = new CheckSocket();
            client.connect(socket,serverWebsocketURI.resolve("/echo"));

            socket.awaitOpen(2,TimeUnit.SECONDS);
            socket.sendText("Hello World");
            socket.close(StatusCode.NORMAL,"Test complete");
            socket.awaitClose(2,TimeUnit.SECONDS);

            assertThat("Messages received",socket.getTextMessages().size(),is(1));
            String response = socket.getTextMessages().poll();
            System.err.println(response);

            assertThat("Message[0]",response,is("Hello World"));
        }
        finally
        {
            client.stop();
        }
    }
