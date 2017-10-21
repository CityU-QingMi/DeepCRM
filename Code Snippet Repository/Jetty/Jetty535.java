    @Test
    public void test_QueuedRequest_IsSent_WhenPreviousRequestSucceeded() throws Exception
    {
        start(new EmptyServerHandler());

        client.setMaxConnectionsPerDestination(1);

        final CountDownLatch latch = new CountDownLatch(1);
        final CountDownLatch successLatch = new CountDownLatch(2);
        client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .onRequestBegin(request ->
                {
                    try
                    {
                        latch.await();
                    }
                    catch (InterruptedException x)
                    {
                        x.printStackTrace();
                    }
                })
                .send(new Response.Listener.Adapter()
                {
                    @Override
                    public void onSuccess(Response response)
                    {
                        Assert.assertEquals(200, response.getStatus());
                        successLatch.countDown();
                    }
                });

        client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .onRequestQueued(request -> latch.countDown())
                .send(new Response.Listener.Adapter()
                {
                    @Override
                    public void onSuccess(Response response)
                    {
                        Assert.assertEquals(200, response.getStatus());
                        successLatch.countDown();
                    }
                });

        Assert.assertTrue(successLatch.await(5, TimeUnit.SECONDS));
    }
