    @Test
    public void testGzipHandlerSet() throws Exception
    {
        ServletContextHandler context = new ServletContextHandler();
        context.setSessionHandler(new SessionHandler());
        context.setGzipHandler(new GzipHandler());
        GzipHandler gzip = context.getGzipHandler();        
        _server.start();
        assertEquals(context.getSessionHandler(),context.getHandler());
        assertEquals(gzip,context.getSessionHandler().getHandler());
        assertEquals(context.getServletHandler(),gzip.getHandler());
    }
