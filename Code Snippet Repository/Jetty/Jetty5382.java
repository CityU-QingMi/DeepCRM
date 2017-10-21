    @Test
    public void testGetFailed() throws Exception
    {
        final FutureCallback fcb= new FutureCallback();
        final Exception ex=new Exception("FAILED");
        final CountDownLatch latch = new CountDownLatch(1);
        
        new Thread(new Runnable(){
            public void run()
            {
                latch.countDown();
                try{TimeUnit.MILLISECONDS.sleep(100);}catch(Exception e){e.printStackTrace();}
                fcb.failed(ex);
            }
        }).start();
        
        latch.await();
        long start=System.currentTimeMillis();
        try
        {
            fcb.get(10000,TimeUnit.MILLISECONDS);
            Assert.fail();
        }
        catch(ExecutionException ee)
        {
            Assert.assertEquals(ex,ee.getCause());
        }
        Assert.assertThat(System.currentTimeMillis()-start,Matchers.greaterThan(10L)); 
        Assert.assertThat(System.currentTimeMillis()-start,Matchers.lessThan(1000L));

        Assert.assertTrue(fcb.isDone());
        Assert.assertFalse(fcb.isCancelled());
    }
