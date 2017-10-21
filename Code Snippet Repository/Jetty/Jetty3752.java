    @Test
    public void testWrapperServerSet()
    {
        Server s=new Server();
        HandlerWrapper a = new HandlerWrapper();
        HandlerWrapper b = new HandlerWrapper();
        HandlerWrapper c = new HandlerWrapper();
        a.setServer(s);
        b.setHandler(c);
        a.setHandler(b);
        
        assertThat(b.getServer(),equalTo(s));
        assertThat(c.getServer(),equalTo(s));
    }
