    @Slow
    @Test
    public void testUploadWithDeferredContentProviderFromInputStream() throws Exception
    {
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                IO.copy(request.getInputStream(), new ByteArrayOutputStream());
            }
        });

        final CountDownLatch latch = new CountDownLatch(1);
        try (DeferredContentProvider content = new DeferredContentProvider())
        {
            client.newRequest("localhost", connector.getLocalPort())
                    .scheme(getScheme())
                    .content(content)
                    .send(result ->
                    {
                        if (result.isSucceeded() && result.getResponse().getStatus() == 200)
                            latch.countDown();
                    });

            // Make sure we provide the content *after* the request has been "sent".
            Thread.sleep(1000);

            try (ByteArrayInputStream input = new ByteArrayInputStream(new byte[1024]))
            {
                byte[] buffer = new byte[200];
                int read;
                while ((read = input.read(buffer)) >= 0)
                    content.offer(ByteBuffer.wrap(buffer, 0, read));
            }
        }
        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
