    @Test
    public void testAbortOnContent() throws Exception
    {
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                OutputStream output = response.getOutputStream();
                output.write(1);
                output.flush();
            }
        });

        client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .onResponseContent(new Response.ContentListener()
                {
                    @Override
                    public void onContent(Response response, ByteBuffer content)
                    {
                        abort(response);
                    }
                })
                .send(new TestResponseListener());
        Assert.assertTrue(callbackLatch.await(5, TimeUnit.SECONDS));
        Assert.assertTrue(completeLatch.await(5, TimeUnit.SECONDS));
        Assert.assertTrue(failureWasAsync.get());
        Assert.assertTrue(completeWasSync.get());
    }
