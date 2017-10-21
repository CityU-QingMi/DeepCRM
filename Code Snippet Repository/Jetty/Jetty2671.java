    @Override
    public Authentication authenticate(ServletRequest request, ServletResponse response)
    {
        try
        {
            LoginService login_service= _authenticator.getLoginService();
            IdentityService identity_service=login_service.getIdentityService();
            
            Authentication authentication = _authenticator.validateRequest(request,response,true);
            if (authentication instanceof Authentication.User && identity_service!=null)
                _previousAssociation=identity_service.associate(((Authentication.User)authentication).getUserIdentity());
            return authentication;
        }
        catch (ServerAuthException e)
        {
            LOG.debug(e);
        }
        return this;
    }
