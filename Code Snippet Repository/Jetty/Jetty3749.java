    @Test
    public void testInsertWrapper()
    {
        HandlerWrapper a = new HandlerWrapper();
        HandlerWrapper b = new HandlerWrapper();
        HandlerWrapper c = new HandlerWrapper();
        
        a.insertHandler(c);
        a.insertHandler(b);
        assertThat(a.getHandler(),equalTo(b));
        assertThat(b.getHandler(),equalTo(c));
        assertThat(c.getHandler(),nullValue());
    }
