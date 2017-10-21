    @Test
    public void testExternalSite() throws Exception
    {
        String host = "wikipedia.org";
        int port = 80;

        // Verify that we have connectivity
        assumeCanConnectTo(host, port);

        final CountDownLatch latch1 = new CountDownLatch(1);
        client.newRequest(host, port).send(new Response.CompleteListener()
        {
            @Override
            public void onComplete(Result result)
            {
                if (!result.isFailed() && result.getResponse().getStatus() == 200)
                    latch1.countDown();
            }
        });
        Assert.assertTrue(latch1.await(10, TimeUnit.SECONDS));

        // Try again the same URI, but without specifying the port
        final CountDownLatch latch2 = new CountDownLatch(1);
        client.newRequest("http://" + host).send(new Response.CompleteListener()
        {
            @Override
            public void onComplete(Result result)
            {
                Assert.assertTrue(result.isSucceeded());
                Assert.assertEquals(200, result.getResponse().getStatus());
                latch2.countDown();
            }
        });
        Assert.assertTrue(latch2.await(10, TimeUnit.SECONDS));
    }
