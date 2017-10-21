    @Test
    public void testNoForwardHeaders() throws Exception
    {
        AtomicReference<String> last = new AtomicReference<>();
        ThreadLimitHandler handler = new ThreadLimitHandler(null,false)
        {
            @Override
            protected int getThreadLimit(String ip)
            {
                last.set(ip);
                return super.getThreadLimit(ip);
            }
        };
        handler.setHandler(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                response.setStatus(HttpStatus.OK_200);
            }
        });
        _server.setHandler(handler);
        _server.start();

        last.set(null);
        _local.getResponse("GET / HTTP/1.0\r\n\r\n");
        Assert.assertThat(last.get(),Matchers.is("0.0.0.0"));
        
        last.set(null);
        _local.getResponse("GET / HTTP/1.0\r\nX-Forwarded-For: 1.2.3.4\r\n\r\n");
        Assert.assertThat(last.get(),Matchers.is("0.0.0.0"));
        
        last.set(null);
        _local.getResponse("GET / HTTP/1.0\r\nForwarded: for=1.2.3.4\r\n\r\n");
        Assert.assertThat(last.get(),Matchers.is("0.0.0.0"));
    }
