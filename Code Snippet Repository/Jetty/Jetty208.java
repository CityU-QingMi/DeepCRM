    @Test
    public void testWebSocketActivated() throws Exception
    {
        WebSocketClient client = new WebSocketClient();
        try
        {
            client.start();
            CheckSocket socket = new CheckSocket();
            client.connect(socket,serverWebsocketURI.resolve("/echo"));

            socket.awaitOpen(2,TimeUnit.SECONDS);
            socket.sendText("Hello");
            socket.close(StatusCode.NORMAL,"Test complete");
            socket.awaitClose(2,TimeUnit.SECONDS);

            assertThat("Messages received",socket.getTextMessages().size(),is(1));
            assertThat("Message[0]",socket.getTextMessages().poll(),is("Hello"));
        }
        finally
        {
            client.stop();
        }
    }
