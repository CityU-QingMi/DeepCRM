    @Test
    public void testBlockerTimeout() throws Exception
    {
        Blocker b0=null;
        try
        {
            try (Blocker blocker=sbcb.acquire())
            {
                b0=blocker;
                Thread.sleep(400);
                blocker.block();
            }
            fail();
        }
        catch(IOException e)
        {
            Throwable cause = e.getCause();
            assertThat(cause,instanceOf(TimeoutException.class));
        }
        
        Assert.assertEquals(0,notComplete.get());
        

        try (Blocker blocker=sbcb.acquire())
        {
            assertThat(blocker,not(equalTo(b0)));
            try
            {
                b0.succeeded();
                fail();
            }
            catch(Exception e)
            {
                assertThat(e,instanceOf(IllegalStateException.class));
                assertThat(e.getCause(),instanceOf(TimeoutException.class));
            }
            blocker.succeeded();
        }
    }
