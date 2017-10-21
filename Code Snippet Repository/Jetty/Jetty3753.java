    @Test
    public void testWrapperThisLoop()
    {
        HandlerWrapper a = new HandlerWrapper();
        
        try
        {
            a.setHandler(a);
            fail();
        }
        catch(IllegalStateException e)
        {
            assertThat(e.getMessage(),containsString("loop"));
        }
    }
