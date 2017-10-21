    @Test
    public void testInsertWrapperTail()
    {
        HandlerWrapper a = new HandlerWrapper();
        HandlerWrapper b = new HandlerWrapper();
        
        a.insertHandler(b);
        assertThat(a.getHandler(),equalTo(b));
        assertThat(b.getHandler(),nullValue());
    }
