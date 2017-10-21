    private void testRequestWithResponseContentChunked(int length) throws Exception
    {
        final byte[] chunk1 = new byte[length];
        final byte[] chunk2 = new byte[length];
        Random random = new Random();
        random.nextBytes(chunk1);
        random.nextBytes(chunk2);
        byte[] bytes = new byte[chunk1.length + chunk2.length];
        System.arraycopy(chunk1, 0, bytes, 0, chunk1.length);
        System.arraycopy(chunk2, 0, bytes, chunk1.length, chunk2.length);
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                ServletOutputStream output = response.getOutputStream();
                output.write(chunk1);
                output.flush();
                output.write(chunk2);
            }
        });

        org.eclipse.jetty.client.api.Request request = client.newRequest(newURI());
        FutureResponseListener listener = new FutureResponseListener(request, 2 * length);
        request.timeout(10, TimeUnit.SECONDS).send(listener);
        ContentResponse response = listener.get();

        Assert.assertEquals(200, response.getStatus());
        Assert.assertArrayEquals(bytes, response.getContent());
    }
