    @Test
    public void testFailed() throws Exception
    {
        final Exception ex = new Exception("FAILED");
        long start=Long.MIN_VALUE;
        try
        {
            try (final Blocker blocker=sbcb.acquire())
            {
                blocker.failed(ex);
                blocker.block();
            }
            Assert.fail();
        }
        catch(IOException ee)
        {
            start=System.currentTimeMillis();
            Assert.assertEquals(ex,ee.getCause());
        }
        Assert.assertThat(System.currentTimeMillis()-start,Matchers.lessThan(100L));    
        Assert.assertEquals(0,notComplete.get());    
    }
