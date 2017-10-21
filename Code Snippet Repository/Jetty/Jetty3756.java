    @Test
    public void testWrapperChainLoop()
    {
        HandlerWrapper a = new HandlerWrapper();
        HandlerWrapper b = new HandlerWrapper();
        HandlerWrapper c = new HandlerWrapper();
        
        a.setHandler(b);
        c.setHandler(a);
        
        try
        {
            b.setHandler(c);
            fail();
        }
        catch(IllegalStateException e)
        {
            assertThat(e.getMessage(),containsString("loop"));
        }
    }
