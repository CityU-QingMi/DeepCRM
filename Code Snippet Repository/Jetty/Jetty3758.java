    @Test
    public void testCollectionServerSet()
    {
        Server s=new Server();
        HandlerCollection a = new HandlerCollection();
        HandlerCollection b = new HandlerCollection();
        HandlerCollection b1 = new HandlerCollection();
        HandlerCollection b2 = new HandlerCollection();
        HandlerCollection c = new HandlerCollection();
        HandlerCollection c1 = new HandlerCollection();
        HandlerCollection c2 = new HandlerCollection();
        
        a.setServer(s);
        a.addHandler(b);
        a.addHandler(c);
        b.setHandlers(new Handler[]{b1,b2});
        c.setHandlers(new Handler[]{c1,c2});
        
        assertThat(b.getServer(),equalTo(s));
        assertThat(c.getServer(),equalTo(s));
        assertThat(b1.getServer(),equalTo(s));
        assertThat(b2.getServer(),equalTo(s));
        assertThat(c1.getServer(),equalTo(s));
        assertThat(c2.getServer(),equalTo(s));
    }
