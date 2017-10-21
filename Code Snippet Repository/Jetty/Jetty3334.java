    @Test
    public void testEmptyFlush() throws Exception
    {
        server.stop();
        server.setHandler(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                response.setStatus(200);
                OutputStream out = response.getOutputStream();
                out.flush();
                out.flush();
            }
        });
        server.start();

        String response=connector.getResponse("GET / HTTP/1.1\r\n"+
            "Host: localhost\r\n"+
            "Connection: close\r\n"+
            "\r\n");

        assertThat(response, Matchers.containsString("200 OK"));
    }
