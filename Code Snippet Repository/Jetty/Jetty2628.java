    @Override
    protected boolean checkUserDataPermissions(String pathInContext, Request request, Response response, RoleInfo roleInfo) throws IOException
    {
        if (roleInfo == null)
            return true;

        if (roleInfo.isForbidden())
            return false;

        UserDataConstraint dataConstraint = roleInfo.getUserDataConstraint();
        if (dataConstraint == null || dataConstraint == UserDataConstraint.None)
            return true;

        HttpConfiguration httpConfig = Request.getBaseRequest(request).getHttpChannel().getHttpConfiguration();

        if (dataConstraint == UserDataConstraint.Confidential || dataConstraint == UserDataConstraint.Integral)
        {
            if (request.isSecure())
                return true;

            if (httpConfig.getSecurePort() > 0)
            {
                String scheme = httpConfig.getSecureScheme();
                int port = httpConfig.getSecurePort();
                
                String url = URIUtil.newURI(scheme, request.getServerName(), port,request.getRequestURI(),request.getQueryString());
                response.setContentLength(0);
                response.sendRedirect(url);
            }
            else
                response.sendError(HttpStatus.FORBIDDEN_403,"!Secure");

            request.setHandled(true);
            return false;
        }
        else
        {
            throw new IllegalArgumentException("Invalid dataConstraint value: " + dataConstraint);
        }

    }
