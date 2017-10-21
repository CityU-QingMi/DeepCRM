    @Test
    public void testHandlerBeforeServletHandler() throws Exception
    {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        
        HandlerWrapper extra = new HandlerWrapper();
        
        context.getSessionHandler().insertHandler(extra);
        
        context.addServlet(TestServlet.class,"/test");
        context.setContextPath("/");
        _server.setHandler(context);
        _server.start();

        StringBuffer request = new StringBuffer();
        request.append("GET /test HTTP/1.0\n");
        request.append("Host: localhost\n");
        request.append("\n");

        String response = _connector.getResponse(request.toString());
        assertResponseContains("Test", response);
        
        assertEquals(extra,context.getSessionHandler().getHandler());
    }
