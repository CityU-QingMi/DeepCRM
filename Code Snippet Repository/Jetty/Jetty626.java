    @Test
    public void testAbortOnHeaders() throws Exception
    {
        start(new EmptyServerHandler());

        client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .onResponseHeaders(new Response.HeadersListener()
                {
                    @Override
                    public void onHeaders(Response response)
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
