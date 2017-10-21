    @Test
    public void testDone() throws Exception
    { 
        long start;
        try (Blocker blocker=sbcb.acquire())
        {
            blocker.succeeded();
            start=System.currentTimeMillis();
            blocker.block();
        }
        Assert.assertThat(System.currentTimeMillis()-start,Matchers.lessThan(500L));  
        Assert.assertEquals(0,notComplete.get());   
    }
