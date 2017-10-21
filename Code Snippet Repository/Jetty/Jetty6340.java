    private void assertResponse(String requestPath, String requestMessage, String expectedResponse) throws Exception
    {
        WebSocketClient client = new WebSocketClient(bufferPool);
        try
        {
            client.start();
            JettyEchoSocket clientEcho = new JettyEchoSocket();
            Future<Session> future = client.connect(clientEcho,serverUri.resolve(requestPath));
            // wait for connect
            future.get(1,TimeUnit.SECONDS);
            clientEcho.sendMessage(requestMessage);
            Queue<String> msgs = clientEcho.awaitMessages(1);
            Assert.assertThat("Expected message",msgs.poll(),is(expectedResponse));
        }
        finally
        {
            client.stop();
        }
    }
