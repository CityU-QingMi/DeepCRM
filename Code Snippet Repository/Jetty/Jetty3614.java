    @Test
    public void testPartialRead() throws Exception
    {
        Handler handler = new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException,
                    ServletException
            {
                baseRequest.setHandled(true);
                Reader reader=request.getReader();
                byte[] b=("read="+reader.read()+"\n").getBytes(StandardCharsets.UTF_8);
                response.setContentLength(b.length);
                response.getOutputStream().write(b);
                response.flushBuffer();
            }
            
        };
        _server.stop();
        _server.setHandler(handler);
        _server.start();
        
        String requests="GET / HTTP/1.1\r\n"+
                "Host: whatever\r\n"+
                "Content-Type: text/plane\r\n"+
                "Content-Length: "+10+"\r\n"+
                "\r\n"+
                "0123456789\r\n"+
                "GET / HTTP/1.1\r\n"+
                "Host: whatever\r\n"+
                "Content-Type: text/plane\r\n"+
                "Content-Length: "+10+"\r\n"+
                "Connection: close\r\n"+
                "\r\n"+
                "ABCDEFGHIJ\r\n";
        
        
        LocalEndPoint endp = _connector.executeRequest(requests);
        String responses = endp.getResponse() + endp.getResponse();
        
        int index=responses.indexOf("read="+(int)'0');
        assertTrue(index>0);
        
        index=responses.indexOf("read="+(int)'A',index+7);
        assertTrue(index>0);
    }
