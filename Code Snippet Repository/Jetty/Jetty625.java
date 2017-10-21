    @Test
    public void testAbortOnHeader() throws Exception
    {
        start(new EmptyServerHandler());

        client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .onResponseHeader(new Response.HeaderListener()
                {
                    @Override
                    public boolean onHeader(Response response, HttpField field)
                    {
                        abort(response);
                        return true;
                    }
                })
                .send(new TestResponseListener());
        Assert.assertTrue(callbackLatch.await(5, TimeUnit.SECONDS));
        Assert.assertTrue(completeLatch.await(5, TimeUnit.SECONDS));
        Assert.assertTrue(failureWasAsync.get());
        Assert.assertTrue(completeWasSync.get());
    }
