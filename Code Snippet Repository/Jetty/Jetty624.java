    @Test
    public void testAbortOnBegin() throws Exception
    {
        start(new EmptyServerHandler());

        client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .onResponseBegin(new Response.BeginListener()
                {
                    @Override
                    public void onBegin(Response response)
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
