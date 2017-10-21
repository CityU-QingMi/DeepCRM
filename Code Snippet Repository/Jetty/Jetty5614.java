    @Test
    public void testGetDone() throws Exception
    {
        long start;
        try (final Blocker blocker=sbcb.acquire())
        {
            final CountDownLatch latch = new CountDownLatch(1);

            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    latch.countDown();
                    try{TimeUnit.MILLISECONDS.sleep(100);}catch(Exception e){e.printStackTrace();}
                    blocker.succeeded();
                }
            }).start();

            latch.await();
            start=System.currentTimeMillis();
            blocker.block();
        }
        Assert.assertThat(System.currentTimeMillis()-start,Matchers.greaterThan(10L)); 
        Assert.assertThat(System.currentTimeMillis()-start,Matchers.lessThan(1000L)); 
        Assert.assertEquals(0,notComplete.get());   
    }
