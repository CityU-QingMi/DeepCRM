    @Test
    public void testSynchronizationOnException() throws Exception
    {
        start(new EmptyServerHandler());
        int port = connector.getLocalPort();
        server.stop();

        int count = 10;
        final CountDownLatch latch = new CountDownLatch(count);
        for (int i = 0; i < count; ++i)
        {
            Request request = client.newRequest("localhost", port)
                    .scheme(scheme);

            synchronized (this)
            {
                request.send(new Response.Listener.Adapter()
                {
                    @Override
                    public void onFailure(Response response, Throwable failure)
                    {
                        synchronized (HttpClientSynchronizationTest.this)
                        {
                            Assert.assertThat(failure, Matchers.instanceOf(ConnectException.class));
                            latch.countDown();
                        }
                    }
                });
            }
        }

        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
