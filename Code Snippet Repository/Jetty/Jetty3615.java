    @Test
    public void testQueryAfterRead()
        throws Exception
    {
        Handler handler = new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException
            {
                baseRequest.setHandled(true);
                Reader reader=request.getReader();
                String in = IO.toString(reader);
                String param = request.getParameter("param");

                byte[] b=("read='"+in+"' param="+param+"\n").getBytes(StandardCharsets.UTF_8);
                response.setContentLength(b.length);
                response.getOutputStream().write(b);
                response.flushBuffer();
            }
        };
        _server.stop();
        _server.setHandler(handler);
        _server.start();

        String request="POST /?param=right HTTP/1.1\r\n"+
        "Host: whatever\r\n"+
        "Content-Type: application/x-www-form-urlencoded\r\n"+
        "Content-Length: "+11+"\r\n"+
        "Connection: close\r\n"+
        "\r\n"+
        "param=wrong\r\n";

        String responses = _connector.getResponse(request);

        assertTrue(responses.indexOf("read='param=wrong' param=right")>0);

    }
