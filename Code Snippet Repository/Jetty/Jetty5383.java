    @Test
    public void testCancelled() throws Exception
    {
        FutureCallback fcb= new FutureCallback();
        fcb.cancel(true);
        Assert.assertTrue(fcb.isDone());
        Assert.assertTrue(fcb.isCancelled());

        long start=System.currentTimeMillis();
        try
        {
            fcb.get();
            Assert.fail();
        }
        catch(CancellationException e)
        {
            Assert.assertThat(e.getCause(),Matchers.instanceOf(CancellationException.class));
        }
        Assert.assertThat(System.currentTimeMillis()-start,Matchers.lessThan(100L));     
    }
