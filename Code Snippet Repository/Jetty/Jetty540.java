    @Test
    public void testAllHeadersDiscarded() throws Exception
    {
        start(new EmptyServerHandler());

        int count = 10;
        final CountDownLatch latch = new CountDownLatch(count);
        for (int i = 0; i < count; ++i)
        {
            client.newRequest("localhost", connector.getLocalPort())
                    .scheme(scheme)
                    .send(new Response.Listener.Adapter()
                    {
                        @Override
                        public boolean onHeader(Response response, HttpField field)
                        {
                            return false;
                        }

                        @Override
                        public void onComplete(Result result)
                        {
                            if (result.isSucceeded())
                                latch.countDown();
                        }
                    });
        }

        Assert.assertTrue(latch.await(10, TimeUnit.SECONDS));
    }
