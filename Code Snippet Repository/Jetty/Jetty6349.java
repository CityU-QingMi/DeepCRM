    @Test
    public void testMoreThanLargestMessageOneByteAtATime() throws Exception
    {
        int size = wsClient.getDefaultMaxBinaryMessageBufferSize() + 16;
        char[] data = randomChars(size);
        URI uri = URI.create("ws://localhost:" + connector.getLocalPort() + PATH);
        ClientTextStreamer client = new ClientTextStreamer();
        Session session = wsClient.connectToServer(client, uri);

        try (Writer output = session.getBasicRemote().getSendWriter())
        {
            for (int i = 0; i < size; ++i)
                output.write(data[i]);
        }

        Assert.assertTrue(client.await(5, TimeUnit.SECONDS));
        Assert.assertArrayEquals(data, client.getEcho());
    }
