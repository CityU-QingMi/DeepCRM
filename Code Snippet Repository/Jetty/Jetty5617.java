    @Test
    public void testAcquireBlocked() throws Exception
    {
        final CountDownLatch latch = new CountDownLatch(1);
        
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    try (Blocker blocker=sbcb.acquire())
                    {
                        latch.countDown();
                        TimeUnit.MILLISECONDS.sleep(100);
                        blocker.succeeded();
                        blocker.block();
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
        
        
        latch.await();
        long start=System.currentTimeMillis();
        try (Blocker blocker=sbcb.acquire())
        {
            Assert.assertThat(System.currentTimeMillis()-start,Matchers.greaterThan(10L)); 
            Assert.assertThat(System.currentTimeMillis()-start,Matchers.lessThan(500L)); 

            blocker.succeeded();
            blocker.block();
        }
        Assert.assertThat(System.currentTimeMillis()-start,Matchers.lessThan(600L)); 
        Assert.assertEquals(0,notComplete.get());     
    }
