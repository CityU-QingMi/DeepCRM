    @Test
    public void testGetCancelled() throws Exception
    {
        final FutureCallback fcb= new FutureCallback();
        final CountDownLatch latch = new CountDownLatch(1);
        
        new Thread(new Runnable(){
            public void run()
            {
                latch.countDown();
                try{TimeUnit.MILLISECONDS.sleep(100);}catch(Exception e){e.printStackTrace();}
                fcb.cancel(true);
            }
        }).start();
        
        latch.await();
        long start=System.currentTimeMillis();
        try
        {
            fcb.get(10000,TimeUnit.MILLISECONDS);
            Assert.fail();
        }
        catch(CancellationException e)
        {
            Assert.assertThat(e.getCause(),Matchers.instanceOf(CancellationException.class));
        }
        Assert.assertThat(System.currentTimeMillis()-start,Matchers.greaterThan(10L)); 
        Assert.assertThat(System.currentTimeMillis()-start,Matchers.lessThan(1000L));

        Assert.assertTrue(fcb.isDone());
        Assert.assertTrue(fcb.isCancelled());
           
    }
