    @Test
    public void testNotAccepting() throws Exception
    {
        _lowResourcesMonitor.setAcceptingInLowResources(false);
        Thread.sleep(1200);
        _threadPool.setMaxThreads(_threadPool.getThreads()-_threadPool.getIdleThreads()+10);
        Thread.sleep(1200);
        Assert.assertFalse(_lowResourcesMonitor.isLowOnResources());
        
        for (AbstractConnector c : _server.getBeans(AbstractConnector.class))
            assertThat(c.isAccepting(),Matchers.is(true));
        
        final CountDownLatch latch = new CountDownLatch(1);
        for (int i=0;i<100;i++)
        {
            _threadPool.execute(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        latch.await();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            });
        }
        
        Thread.sleep(1200);
        Assert.assertTrue(_lowResourcesMonitor.isLowOnResources());
        for (AbstractConnector c : _server.getBeans(AbstractConnector.class))
            assertThat(c.isAccepting(),Matchers.is(false));
        
        latch.countDown();
        Thread.sleep(1200);
        Assert.assertFalse(_lowResourcesMonitor.isLowOnResources());   
        for (AbstractConnector c : _server.getBeans(AbstractConnector.class))
            assertThat(c.isAccepting(),Matchers.is(true));   
    }
