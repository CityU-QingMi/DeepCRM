    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if (HttpMethod.CONNECT.is(request.getMethod()))
        {
            String serverAddress = request.getRequestURI();
            if (LOG.isDebugEnabled())
                LOG.debug("CONNECT request for {}", serverAddress);

            handleConnect(baseRequest, request, response, serverAddress);
        }
        else
        {
            super.handle(target, baseRequest, request, response);
        }
    }
