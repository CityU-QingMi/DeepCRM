    @Test
    public void testMoreThanLargestMessageOneByteAtATime() throws Exception
    {
        int size = wsClient.getDefaultMaxBinaryMessageBufferSize() + 16;
        byte[] data = randomBytes(size);
        URI uri = URI.create("ws://localhost:" + connector.getLocalPort() + PATH);
        ClientBinaryStreamer client = new ClientBinaryStreamer();
        Session session = wsClient.connectToServer(client, uri);

        try (OutputStream output = session.getBasicRemote().getSendStream())
        {
            for (int i = 0; i < size; ++i)
                output.write(data[i]);
        }

        Assert.assertTrue(client.await(5, TimeUnit.SECONDS));
        Assert.assertArrayEquals(data, client.getEcho());
    }
