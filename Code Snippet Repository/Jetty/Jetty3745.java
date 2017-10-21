    @Test
    public void testWrapperSetServer()
    {
        Server s=new Server();
        HandlerWrapper a = new HandlerWrapper();
        HandlerWrapper b = new HandlerWrapper();
        HandlerWrapper c = new HandlerWrapper();
        a.setHandler(b);
        b.setHandler(c);
        
        a.setServer(s);
        assertThat(b.getServer(),equalTo(s));
        assertThat(c.getServer(),equalTo(s));
    }
