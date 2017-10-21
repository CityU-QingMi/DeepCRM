    @Test
    public void testBigUploadWithOutputStreamFromInputStream() throws Exception
    {
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                IO.copy(request.getInputStream(), response.getOutputStream());
            }
        });

        final byte[] data = new byte[16 * 1024 * 1024];
        new Random().nextBytes(data);
        final CountDownLatch latch = new CountDownLatch(1);
        OutputStreamContentProvider content = new OutputStreamContentProvider();
        client.newRequest("localhost", connector.getLocalPort())
                .scheme(getScheme())
                .content(content)
                .send(new BufferingResponseListener(data.length)
                {
                    @Override
                    public void onComplete(Result result)
                    {
                        Assert.assertTrue(result.isSucceeded());
                        Assert.assertEquals(200, result.getResponse().getStatus());
                        Assert.assertArrayEquals(data, getContent());
                        latch.countDown();
                    }
                });

        // Make sure we provide the content *after* the request has been "sent".
        Thread.sleep(1000);

        try (InputStream input = new ByteArrayInputStream(data); OutputStream output = content.getOutputStream())
        {
            byte[] buffer = new byte[1024];
            while (true)
            {
                int read = input.read(buffer);
                if (read < 0)
                    break;
                output.write(buffer, 0, read);
            }
        }

        Assert.assertTrue(latch.await(30, TimeUnit.SECONDS));
    }
