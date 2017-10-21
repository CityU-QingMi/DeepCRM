    @Test
    public void testSessionAfterRedirect() throws Exception
    {
        Handler handler = new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException
            {
                baseRequest.setHandled(true);
                response.sendRedirect("/foo");
                try
                {
                    request.getSession(true);
                    fail("Session should not be created after response committed");
                }
                catch (IllegalStateException e)
                {
                    //expected
                }
                catch (Exception e)
                {
                    fail("Session creation after response commit should throw IllegalStateException");
                }
            }
        };
        _server.stop();
        _server.setHandler(handler);
        _server.start();
        String response=_connector.getResponse("GET / HTTP/1.1\n"+
                                                "Host: myhost\n"+
                                                "Connection: close\n"+
                                                "\n");
        assertThat(response, containsString(" 302 Found"));
        assertThat(response, containsString("Location: http://myhost/foo"));
    }
