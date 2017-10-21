    @Test
    public void testTimeoutCancelledWhenSendingThrowsException() throws Exception
    {
        start(new EmptyServerHandler());

        long timeout = 1000;
        Request request = client.newRequest("badscheme://localhost:" + connector.getLocalPort());

        try
        {
            request.timeout(timeout, TimeUnit.MILLISECONDS)
                    .send(new Response.CompleteListener()
                    {
                        @Override
                        public void onComplete(Result result)
                        {
                        }
                    });
            Assert.fail();
        }
        catch (Exception expected)
        {
        }

        Thread.sleep(2 * timeout);

        // If the task was not cancelled, it aborted the request.
        Assert.assertNull(request.getAbortCause());
    }
