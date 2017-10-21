    @Test
    public void testExternalSSLSite() throws Exception
    {
        client.stop();
        client = new HttpClient(new SslContextFactory());
        client.start();

        String host = "api-3t.paypal.com";
        int port = 443;

        // Verify that we have connectivity
        assumeCanConnectTo(host, port);

        final CountDownLatch latch = new CountDownLatch(1);
        client.newRequest(host, port).scheme("https").path("/nvp").send(new Response.CompleteListener()
        {
            @Override
            public void onComplete(Result result)
            {
                if (result.isSucceeded() && result.getResponse().getStatus() == 200)
                    latch.countDown();
            }
        });
        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
