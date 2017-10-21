    private void testRequestWithResponseContent(int length) throws Exception
    {
        final byte[] bytes = new byte[length];
        new Random().nextBytes(bytes);
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                response.setContentLength(length);
                response.getOutputStream().write(bytes);
            }
        });

        org.eclipse.jetty.client.api.Request request = client.newRequest(newURI());
        FutureResponseListener listener = new FutureResponseListener(request, length);
        request.timeout(10, TimeUnit.SECONDS).send(listener);
        ContentResponse response = listener.get();

        Assert.assertEquals(200, response.getStatus());
        Assert.assertArrayEquals(bytes, response.getContent());
    }
