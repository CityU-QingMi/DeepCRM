    @Test
    public void testInsertWrapperChain()
    {
        HandlerWrapper a = new HandlerWrapper();
        HandlerWrapper b = new HandlerWrapper();
        HandlerWrapper c = new HandlerWrapper();
        HandlerWrapper d = new HandlerWrapper();
        
        a.insertHandler(d);
        b.insertHandler(c);
        a.insertHandler(b);
        assertThat(a.getHandler(),equalTo(b));
        assertThat(b.getHandler(),equalTo(c));
        assertThat(c.getHandler(),equalTo(d));
        assertThat(d.getHandler(),nullValue());
    }
