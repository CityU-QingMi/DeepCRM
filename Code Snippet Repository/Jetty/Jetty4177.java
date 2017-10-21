    @Test
    public void testGzipHandlerOption() throws Exception
    {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS|ServletContextHandler.GZIP);
        GzipHandler gzip = context.getGzipHandler();        
        _server.start();
        assertEquals(context.getSessionHandler(),context.getHandler());
        assertEquals(gzip,context.getSessionHandler().getHandler());
        assertEquals(context.getServletHandler(),gzip.getHandler());
    }
