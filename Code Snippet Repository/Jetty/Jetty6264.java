    private void assertResponse(String message, String... expectedTexts) throws Exception
    {
        WebSocketClient client = new WebSocketClient(bufferPool);
        try
        {
            client.start();
            JettyEchoSocket clientEcho = new JettyEchoSocket();
            URI uri = server.getServerBaseURI().resolve("echo");
            ClientUpgradeRequest req = new ClientUpgradeRequest();
            req.setSubProtocols("echo");
            Future<Session> foo = client.connect(clientEcho,uri,req);
            // wait for connect
            foo.get(1,TimeUnit.SECONDS);

            clientEcho.sendMessage(message);
            Queue<String> msgs = clientEcho.awaitMessages(1);

            String response = msgs.poll();
            for (String expected : expectedTexts)
            {
                Assert.assertThat("Expected message",response,containsString(expected));
            }
        }
        finally
        {
            client.stop();
        }
    }
