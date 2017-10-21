    @Test
    public void testExternalSiteWrongProtocol() throws Exception
    {
        String host = "github.com";
        int port = 22; // SSH port

        // Verify that we have connectivity
        assumeCanConnectTo(host, port);

        for (int i = 0; i < 2; ++i)
        {
            final CountDownLatch latch = new CountDownLatch(3);
            client.newRequest(host, port)
                    .onResponseFailure(new Response.FailureListener()
                    {
                        @Override
                        public void onFailure(Response response, Throwable failure)
                        {
                            latch.countDown();
                        }
                    })
                    .send(new Response.Listener.Adapter()
                    {
                        @Override
                        public void onFailure(Response response, Throwable failure)
                        {
                            latch.countDown();
                        }

                        @Override
                        public void onComplete(Result result)
                        {
                            Assert.assertTrue(result.isFailed());
                            latch.countDown();
                        }
                    });
            Assert.assertTrue(latch.await(10, TimeUnit.SECONDS));
        }
    }
