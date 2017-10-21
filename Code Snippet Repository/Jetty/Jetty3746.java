    @Test
    public void testCollectionDeepLoop()
    {
        HandlerCollection a = new HandlerCollection();
        HandlerCollection b = new HandlerCollection();
        HandlerCollection b1 = new HandlerCollection();
        HandlerCollection b2 = new HandlerCollection();
        HandlerCollection c = new HandlerCollection();
        HandlerCollection c1 = new HandlerCollection();
        HandlerCollection c2 = new HandlerCollection();
        
        a.addHandler(b);
        a.addHandler(c);
        b.setHandlers(new Handler[]{b1,b2});
        c.setHandlers(new Handler[]{c1,c2});

        try
        {
            b2.addHandler(a);
            fail();
        }
        catch(IllegalStateException e)
        {
            assertThat(e.getMessage(),containsString("loop"));
        }
    }
