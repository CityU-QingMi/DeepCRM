    @Test
    public void testFailed() throws Exception
    {
        FutureCallback fcb= new FutureCallback();
        Exception ex=new Exception("FAILED");
        fcb.failed(ex);
        Assert.assertTrue(fcb.isDone());
        Assert.assertFalse(fcb.isCancelled());

        long start=System.currentTimeMillis();
        try
        {
            fcb.get();
            Assert.fail();
        }
        catch(ExecutionException ee)
        {
            Assert.assertEquals(ex,ee.getCause());
        }
        Assert.assertThat(System.currentTimeMillis()-start,Matchers.lessThan(100L));     
    }
