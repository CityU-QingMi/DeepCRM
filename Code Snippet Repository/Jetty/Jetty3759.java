    @Test
    public void testCollectionThisLoop()
    {
        HandlerCollection a = new HandlerCollection();
        
        try
        {
            a.addHandler(a);
            fail();
        }
        catch(IllegalStateException e)
        {
            assertThat(e.getMessage(),containsString("loop"));
        }
    }
