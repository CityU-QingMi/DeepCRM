    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        HttpChannel channel = baseRequest.getHttpChannel();
        if (baseRequest.isSecure() || (channel == null))
        {
            // nothing to do
            return;
        }

        HttpConfiguration httpConfig = channel.getHttpConfiguration();
        if (httpConfig == null)
        {
            // no config, show error
            response.sendError(HttpStatus.FORBIDDEN_403,"No http configuration available");
            return;
        }

        if (httpConfig.getSecurePort() > 0)
        {
            String scheme = httpConfig.getSecureScheme();
            int port = httpConfig.getSecurePort();

            String url = URIUtil.newURI(scheme,baseRequest.getServerName(),port,baseRequest.getRequestURI(),baseRequest.getQueryString());
            response.setContentLength(0);
            response.sendRedirect(url);
        }
        else
        {
            response.sendError(HttpStatus.FORBIDDEN_403,"Not Secure");
        }
        
        baseRequest.setHandled(true);
    }
