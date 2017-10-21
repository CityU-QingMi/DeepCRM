    @Test
    public void testDownloadWithCloseEndOfContent() throws Exception
    {
        final byte[] data = new byte[1024];
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                response.getOutputStream().write(data);
                response.flushBuffer();
            }
        });

        InputStreamResponseListener listener = new InputStreamResponseListener();
        client.newRequest("localhost", connector.getLocalPort())
                .scheme(getScheme())
                .send(listener);
        Response response = listener.get(5, TimeUnit.SECONDS);
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());

        InputStream input = listener.getInputStream();
        Assert.assertNotNull(input);

        for (byte datum : data)
            Assert.assertEquals(datum, input.read());

        // Read EOF
        Assert.assertEquals(-1, input.read());

        input.close();

        // Must not throw
        Assert.assertEquals(-1, input.read());
    }
