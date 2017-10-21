    @Test
    public void testPerMessageDeflateExtension() throws Exception
    {
        assumeDeflateFrameAvailable();
        
        ClientEndpointConfig config = ClientEndpointConfig.Builder.create()
                .extensions(Arrays.<Extension>asList(new JsrExtension("permessage-deflate")))
                .build();

        final String content = "deflate_me";
        final CountDownLatch messageLatch = new CountDownLatch(1);
        URI uri = URI.create("ws://localhost:" + connector.getLocalPort());
        Session session = client.connectToServer(new EndpointAdapter()
        {
            @Override
            public void onMessage(String message)
            {
                Assert.assertEquals(content, message);
                messageLatch.countDown();
            }
        }, config, uri);

        final CountDownLatch latch = new CountDownLatch(1);
        session.getAsyncRemote().sendText(content, new SendHandler()
        {
            @Override
            public void onResult(SendResult result)
            {
                latch.countDown();
            }
        });

        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
        Assert.assertTrue(messageLatch.await(5, TimeUnit.SECONDS));
    }
