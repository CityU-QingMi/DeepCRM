    private void testEcho(int size) throws Exception
    {
        char[] data = randomChars(size);
        URI uri = URI.create("ws://localhost:" + connector.getLocalPort() + PATH);
        ClientTextStreamer client = new ClientTextStreamer();
        Session session = wsClient.connectToServer(client, uri);

        try (Writer output = session.getBasicRemote().getSendWriter())
        {
             output.write(data);
        }

        Assert.assertTrue(client.await(5, TimeUnit.SECONDS));
        Assert.assertArrayEquals(data, client.getEcho());
    }
