    private void testEcho(int size) throws Exception
    {
        byte[] data = randomBytes(size);
        URI uri = URI.create("ws://localhost:" + connector.getLocalPort() + PATH);
        ClientBinaryStreamer client = new ClientBinaryStreamer();
        Session session = wsClient.connectToServer(client, uri);

        try (OutputStream output = session.getBasicRemote().getSendStream())
        {
             output.write(data);
        }

        Assert.assertTrue(client.await(5, TimeUnit.SECONDS));
        Assert.assertArrayEquals(data, client.getEcho());
    }
