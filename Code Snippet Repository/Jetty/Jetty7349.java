    @Test(expected = AsynchronousCloseException.class)
    public void testInputStreamResponseListenerClosedBeforeReading() throws Exception
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

        InputStreamResponseListener listener = new InputStreamResponseListener();
        InputStream stream = listener.getInputStream();
        // Close the stream immediately.
        stream.close();

        client.newRequest("localhost", connector.getLocalPort())
                .scheme(getScheme())
                .content(new BytesContentProvider(new byte[]{0, 1, 2, 3}))
                .send(listener);
        Response response = listener.get(5, TimeUnit.SECONDS);
        Assert.assertEquals(200, response.getStatus());

        stream.read(); // Throws
    }
