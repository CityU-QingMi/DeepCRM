    @Test
    public void testClientIdleTimeout() throws Exception
    {
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                if (target.equals("/timeout"))
                {
                    AsyncContext asyncContext = request.startAsync();
                    asyncContext.setTimeout(0);
                }
            }
        });
        client.stop();
        client.setIdleTimeout(idleTimeout);
        client.start();

        final CountDownLatch latch = new CountDownLatch(1);
        client.newRequest(newURI())
                .path("/timeout")
                .send(result ->
                {
                    if (result.isFailed())
                        latch.countDown();
                });

        Assert.assertTrue(latch.await(2 * idleTimeout, TimeUnit.MILLISECONDS));

        // Verify that after the timeout we can make another request.
        ContentResponse response = client.newRequest(newURI()).send();
        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
    }
