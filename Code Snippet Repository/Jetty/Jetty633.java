    @Override
    public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        baseRequest.setHandled(true);
        response.setContentLength(0);
        response.setStatus(200);
        response.flushBuffer();
        
        InputStream in = request.getInputStream();
        while(in.read()>=0);
    }
