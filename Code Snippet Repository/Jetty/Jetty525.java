    @Test
    public void testSynchronizationOnComplete() throws Exception
    {
        start(new EmptyServerHandler());

        int count = 10;
        final CountDownLatch latch = new CountDownLatch(count);
        for (int i = 0; i < count; ++i)
        {
            Request request = client.newRequest("localhost", connector.getLocalPort())
                    .scheme(scheme);

            synchronized (this)
            {
                request.send(new Response.Listener.Adapter()
                {
                    @Override
                    public void onComplete(Result result)
                    {
                        synchronized (HttpClientSynchronizationTest.this)
                        {
                            Assert.assertFalse(result.isFailed());
                            latch.countDown();
                        }
                    }
                });
            }
        }

        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
