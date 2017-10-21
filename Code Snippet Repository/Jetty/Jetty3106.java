    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        if (!target.equals("/shutdown"))
        {
            super.handle(target,baseRequest,request,response);
            return;
        }

        if (!request.getMethod().equals("POST"))
        {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        if (!hasCorrectSecurityToken(request))
        {
            LOG.warn("Unauthorized tokenless shutdown attempt from " + request.getRemoteAddr());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        if (!requestFromLocalhost(baseRequest))
        {
            LOG.warn("Unauthorized non-loopback shutdown attempt from " + request.getRemoteAddr());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        LOG.info("Shutting down by request from " + request.getRemoteAddr());
        doShutdown(baseRequest, response);
    }
