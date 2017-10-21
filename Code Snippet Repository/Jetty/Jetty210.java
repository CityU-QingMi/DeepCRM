    @Test
    public void testWebSocket_Info_DataFromCdi() throws Exception
    {
        WebSocketClient client = new WebSocketClient();
        try
        {
            client.start();
            CheckSocket socket = new CheckSocket();
            client.connect(socket,serverWebsocketURI.resolve("/cdi-info"));

            socket.awaitOpen(2,TimeUnit.SECONDS);
            socket.sendText("data|stuff");
            socket.close(StatusCode.NORMAL,"Test complete");
            socket.awaitClose(2,TimeUnit.SECONDS);

            assertThat("Messages received",socket.getTextMessages().size(),is(2));
            String response = socket.getTextMessages().poll();
            System.out.println("[0]" + response);
            assertThat("Message[0]",response,containsString("Hello there stuff"));
            System.out.println("[1]" + socket.getTextMessages().poll());
        }
        finally
        {
            client.stop();
        }
    }
