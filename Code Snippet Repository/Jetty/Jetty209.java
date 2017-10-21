    @Test
    public void testWebSocket_Info_FieldPresence() throws Exception
    {
        WebSocketClient client = new WebSocketClient();
        try
        {
            client.start();
            CheckSocket socket = new CheckSocket();
            client.connect(socket,serverWebsocketURI.resolve("/cdi-info"));

            socket.awaitOpen(2,TimeUnit.SECONDS);
            socket.sendText("info");
            socket.close(StatusCode.NORMAL,"Test complete");
            socket.awaitClose(2,TimeUnit.SECONDS);

            assertThat("Messages received",socket.getTextMessages().size(),is(1));
            String response = socket.getTextMessages().poll();
            System.err.println(response);

            assertThat("Message[0]",response,
                    allOf(
                            containsString("websocketSession is PRESENT"),
                            containsString("httpSession is PRESENT"),
                            containsString("servletContext is PRESENT")
                    ));
        }
        finally
        {
            client.stop();
        }
    }
