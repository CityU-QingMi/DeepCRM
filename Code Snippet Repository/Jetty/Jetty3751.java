    @Test
    public void testInsertWrapperBadChain()
    {
        HandlerWrapper a = new HandlerWrapper();
        HandlerWrapper b = new HandlerWrapper();
        HandlerWrapper c = new HandlerWrapper();
        HandlerWrapper d = new HandlerWrapper();
        
        a.insertHandler(d);
        b.insertHandler(c);
        c.setHandler(new AbstractHandler()
        {   
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {                
            }
        });
        
        try
        {
            a.insertHandler(b);
            fail();
        }
        catch(IllegalArgumentException e)
        {
            assertThat(e.getMessage(),containsString("bad tail"));
        }
    }
