    @Test
    public void testShutdownServerWithCorrectTokenAndIP() throws Exception
    {
        start(null);

        CountDownLatch stopLatch = new CountDownLatch(1);
        server.addLifeCycleListener(new AbstractLifeCycle.AbstractLifeCycleListener()
        {
            @Override
            public void lifeCycleStopped(LifeCycle event)
            {
                stopLatch.countDown();
            }
        });

        HttpTester.Response response = shutdown(shutdownToken);
        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());

        Assert.assertTrue(stopLatch.await(5, TimeUnit.SECONDS));
        Assert.assertEquals(AbstractLifeCycle.STOPPED, server.getState());
    }
