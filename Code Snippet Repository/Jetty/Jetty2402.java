    @Test
    public void testProxyRequestHeadersNotSentUntilContent() throws Exception
    {
        startServer(new EchoHttpServlet());
        final CountDownLatch proxyRequestLatch = new CountDownLatch(1);
        startProxy(new AsyncMiddleManServlet()
        {
            @Override
            protected ContentTransformer newClientRequestContentTransformer(HttpServletRequest clientRequest, Request proxyRequest)
            {
                return new BufferingContentTransformer();
            }

            @Override
            protected void sendProxyRequest(HttpServletRequest clientRequest, HttpServletResponse proxyResponse, Request proxyRequest)
            {
                proxyRequestLatch.countDown();
                super.sendProxyRequest(clientRequest, proxyResponse, proxyRequest);
            }
        });
        startClient();

        DeferredContentProvider content = new DeferredContentProvider();
        Request request = client.newRequest("localhost", serverConnector.getLocalPort())
                .timeout(5, TimeUnit.SECONDS)
                .content(content);
        FutureResponseListener listener = new FutureResponseListener(request);
        request.send(listener);

        // Send one chunk of content, the proxy request must not be sent.
        ByteBuffer chunk1 = ByteBuffer.allocate(1024);
        content.offer(chunk1);
        Assert.assertFalse(proxyRequestLatch.await(1, TimeUnit.SECONDS));

        // Send another chunk of content, the proxy request must not be sent.
        ByteBuffer chunk2 = ByteBuffer.allocate(512);
        content.offer(chunk2);
        Assert.assertFalse(proxyRequestLatch.await(1, TimeUnit.SECONDS));

        // Finish the content, request must be sent.
        content.close();
        Assert.assertTrue(proxyRequestLatch.await(1, TimeUnit.SECONDS));

        ContentResponse response = listener.get(5, TimeUnit.SECONDS);
        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
        Assert.assertEquals(chunk1.capacity() + chunk2.capacity(), response.getContent().length);
    }
