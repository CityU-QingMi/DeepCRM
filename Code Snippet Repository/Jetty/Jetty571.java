    @Slow
    @Test(expected = TimeoutException.class)
    public void testTimeoutOnFuture() throws Exception
    {
        long timeout = 1000;
        start(new TimeoutHandler(2 * timeout));

        client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .timeout(timeout, TimeUnit.MILLISECONDS)
                .send();
    }
