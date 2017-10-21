    private void testContentDelimitedByEOFWithSlowRequest(final HttpVersion version, int length) throws Exception
    {
        // This test is crafted in a way that the response completes before the request is fully written.
        // With SSL, the response coming down will close the SSLEngine so it would not be possible to
        // write the last chunk of the request content, and the request will be failed, failing also the
        // test, which is not what we want.
        // This is a limit of Java's SSL implementation that does not allow half closes.
        Assume.assumeTrue(sslContextFactory == null);

        final byte[] data = new byte[length];
        new Random().nextBytes(data);
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                // Send Connection: close to avoid that the server chunks the content with HTTP 1.1.
                if (version.compareTo(HttpVersion.HTTP_1_0) > 0)
                    response.setHeader("Connection", "close");
                response.getOutputStream().write(data);
            }
        });

        DeferredContentProvider content = new DeferredContentProvider(ByteBuffer.wrap(new byte[]{0}));
        Request request = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .version(version)
                .content(content);
        FutureResponseListener listener = new FutureResponseListener(request);
        request.send(listener);
        // Wait some time to simulate a slow request.
        Thread.sleep(1000);
        content.close();

        ContentResponse response = listener.get(5, TimeUnit.SECONDS);

        Assert.assertEquals(200, response.getStatus());
        Assert.assertArrayEquals(data, response.getContent());
    }
