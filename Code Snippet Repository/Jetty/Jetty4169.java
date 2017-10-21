    @Test
    public void testFallThrough() throws Exception
    {
        HandlerList list = new HandlerList();
        _server.setHandler(list);

        ServletContextHandler root = new ServletContextHandler(list,"/",ServletContextHandler.SESSIONS);

        ServletHandler servlet = root.getServletHandler();
        servlet.setEnsureDefaultServlet(false);
        servlet.addServletWithMapping(HelloServlet.class, "/hello/*");
        
        list.addHandler(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                response.sendError(404, "Fell Through");
            }
        });

        _server.start();

        String response= _connector.getResponse("GET /hello HTTP/1.0\r\n\r\n");
        Assert.assertThat(response, Matchers.containsString("200 OK"));
        
        response= _connector.getResponse("GET /other HTTP/1.0\r\n\r\n");
        Assert.assertThat(response, Matchers.containsString("404 Fell Through"));
        
    }
