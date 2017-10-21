    @Test
    public void testGetNotDone() throws Exception
    {
        FutureCallback fcb= new FutureCallback();
        
        long start=System.currentTimeMillis();
        try
        {
            fcb.get(500,TimeUnit.MILLISECONDS);
            Assert.fail();
        }
        catch(TimeoutException e)
        {
        }
        Assert.assertThat(System.currentTimeMillis()-start,Matchers.greaterThan(50L));
    }
