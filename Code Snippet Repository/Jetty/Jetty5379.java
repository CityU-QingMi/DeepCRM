    @Test
    public void testDone() throws Exception
    {
        FutureCallback fcb= new FutureCallback();
        fcb.succeeded();
        Assert.assertTrue(fcb.isDone());
        Assert.assertFalse(fcb.isCancelled());

        long start=System.currentTimeMillis();
        Assert.assertEquals(null,fcb.get());
        Assert.assertThat(System.currentTimeMillis()-start,Matchers.lessThan(500L));     
    }
