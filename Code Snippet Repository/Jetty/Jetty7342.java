    @Test
    public void testUploadWithOutputStream() throws Exception
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

        final byte[] data = new byte[512];
        final CountDownLatch latch = new CountDownLatch(1);
        OutputStreamContentProvider content = new OutputStreamContentProvider();
        client.newRequest("localhost", connector.getLocalPort())
                .scheme(getScheme())
                .content(content)
                .send(new BufferingResponseListener()
                {
                    @Override
                    public void onComplete(Result result)
                    {
                        if (result.isSucceeded() &&
                                result.getResponse().getStatus() == 200 &&
                                Arrays.equals(data, getContent()))
                            latch.countDown();
                    }
                });

        // Make sure we provide the content *after* the request has been "sent".
        Thread.sleep(1000);

        try (OutputStream output = content.getOutputStream())
        {
            output.write(data);
        }

        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
