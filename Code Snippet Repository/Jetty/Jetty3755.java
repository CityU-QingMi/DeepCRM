    @Test
    public void testWrapperDeepLoop()
    {
        HandlerWrapper a = new HandlerWrapper();
        HandlerWrapper b = new HandlerWrapper();
        HandlerWrapper c = new HandlerWrapper();
        
        a.setHandler(b);
        b.setHandler(c);
        
        try
        {
            c.setHandler(a);
            fail();
        }
        catch(IllegalStateException e)
        {
            assertThat(e.getMessage(),containsString("loop"));
        }
    }
