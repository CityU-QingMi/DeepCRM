    @Test
    public void testGETAsync() throws Exception
    {
        HttpClient client = new HttpClient();
        client.start();

        final AtomicReference<Response> responseRef = new AtomicReference<>();
        final CountDownLatch latch = new CountDownLatch(1);

        client.newRequest("localhost", 8080)
                // Send asynchronously
                .send(new Response.CompleteListener()
        {
            @Override
            public void onComplete(Result result)
            {
                if (result.isSucceeded())
                {
                    responseRef.set(result.getResponse());
                    latch.countDown();
                }
            }
        });

        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
        Response response = responseRef.get();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
    }
