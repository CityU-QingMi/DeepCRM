    @Test
    public void testDeflateFrameExtension() throws Exception
    {
        assumeDeflateFrameAvailable();
        
        ClientEndpointConfig config = ClientEndpointConfig.Builder.create()
                .extensions(Arrays.<Extension>asList(new JsrExtension("deflate-frame")))
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

        // Make sure everything is wired properly.
        OutgoingFrames firstOut = ((JsrSession)session).getOutgoingHandler();
        Assert.assertTrue(firstOut instanceof ExtensionStack);
        ExtensionStack extensionStack = (ExtensionStack)firstOut;
        Assert.assertTrue(extensionStack.isRunning());
        OutgoingFrames secondOut = extensionStack.getNextOutgoing();
        Assert.assertTrue(secondOut instanceof DeflateFrameExtension);
        DeflateFrameExtension deflateExtension = (DeflateFrameExtension)secondOut;
        Assert.assertTrue(deflateExtension.isRunning());
        OutgoingFrames thirdOut = deflateExtension.getNextOutgoing();
        Assert.assertTrue(thirdOut instanceof WebSocketClientConnection);

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
