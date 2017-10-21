    @Test
    public void testGetDone() throws Exception
    {
        final FutureCallback fcb= new FutureCallback();
        final CountDownLatch latch = new CountDownLatch(1);
        
        new Thread(new Runnable(){
            public void run()
            {
                latch.countDown();
                try{TimeUnit.MILLISECONDS.sleep(100);}catch(Exception e){e.printStackTrace();}
                fcb.succeeded();
            }
        }).start();
        
        latch.await();
        long start=System.currentTimeMillis();
        Assert.assertEquals(null,fcb.get(10000,TimeUnit.MILLISECONDS));
        Assert.assertThat(System.currentTimeMillis()-start,Matchers.greaterThan(10L)); 
        Assert.assertThat(System.currentTimeMillis()-start,Matchers.lessThan(1000L)); 
        
        Assert.assertTrue(fcb.isDone());
        Assert.assertFalse(fcb.isCancelled());   
    }
