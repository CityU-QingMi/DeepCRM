    @Test
    public void testFieldDeferred() throws Exception
    {
        String name = "field";
        byte[] data = "Hello, World".getBytes(StandardCharsets.US_ASCII);
        start(new AbstractMultiPartHandler()
        {
            @Override
            protected void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                Collection<Part> parts = request.getParts();
                Assert.assertEquals(1, parts.size());
                Part part = parts.iterator().next();
                Assert.assertEquals(name, part.getName());
                Assert.assertEquals("text/plain", part.getContentType());
                Assert.assertArrayEquals(data, IO.readBytes(part.getInputStream()));
            }
        });

        MultiPartContentProvider multiPart = new MultiPartContentProvider();
        DeferredContentProvider content = new DeferredContentProvider();
        multiPart.addFieldPart(name, content, null);
        multiPart.close();
        CountDownLatch responseLatch = new CountDownLatch(1);
        client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .method(HttpMethod.POST)
                .content(multiPart)
                .send(result ->
                {
                    Assert.assertTrue(String.valueOf(result.getFailure()), result.isSucceeded());
                    Assert.assertEquals(200, result.getResponse().getStatus());
                    responseLatch.countDown();
                });

        // Wait until the request has been sent.
        Thread.sleep(1000);

        // Provide the content.
        content.offer(ByteBuffer.wrap(data));
        content.close();

        Assert.assertTrue(responseLatch.await(5, TimeUnit.SECONDS));
    }
