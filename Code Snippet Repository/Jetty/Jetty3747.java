    @Test
    public void testCollectionChainLoop()
    {
        HandlerCollection a = new HandlerCollection();
        HandlerCollection b = new HandlerCollection();
        HandlerCollection b1 = new HandlerCollection();
        HandlerCollection b2 = new HandlerCollection();
        HandlerCollection c = new HandlerCollection();
        HandlerCollection c1 = new HandlerCollection();
        HandlerCollection c2 = new HandlerCollection();
        
        a.addHandler(c);
        b.setHandlers(new Handler[]{b1,b2});
        c.setHandlers(new Handler[]{c1,c2});
        b2.addHandler(a);

        try
        {
            a.addHandler(b);
            fail();
        }
        catch(IllegalStateException e)
        {
            assertThat(e.getMessage(),containsString("loop"));
        }
    }
