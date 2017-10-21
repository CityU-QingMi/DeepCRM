    @Test
    public void testRequestAfterFailedRequest() throws Exception
    {
        int length = FlowControlStrategy.DEFAULT_WINDOW_SIZE;
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                try
                {
                    baseRequest.setHandled(true);
                    response.getOutputStream().write(new byte[length]);
                }
                catch(IOException e)
                {}
            }
        });

        // Make a request with a large enough response buffer.
        org.eclipse.jetty.client.api.Request request = client.newRequest(newURI());
        FutureResponseListener listener = new FutureResponseListener(request, length);
        request.send(listener);
        ContentResponse response = listener.get(5, TimeUnit.SECONDS);
        Assert.assertEquals(response.getStatus(), 200);

        // Make a request with a small response buffer, should fail.
        try
        {
            request = client.newRequest(newURI());
            listener = new FutureResponseListener(request, length / 10);
            request.send(listener);
            listener.get(5, TimeUnit.SECONDS);
            Assert.fail();
        }
        catch (ExecutionException x)
        {
            Assert.assertThat(x.getMessage(),Matchers.containsString("Buffering capacity exceeded"));
        }

        // Verify that we can make another request.
        request = client.newRequest(newURI());
        listener = new FutureResponseListener(request, length);
        request.send(listener);
        response = listener.get(5, TimeUnit.SECONDS);
        Assert.assertEquals(response.getStatus(), 200);
    }
