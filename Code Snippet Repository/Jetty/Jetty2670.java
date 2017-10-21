    @Override
    public Authentication authenticate(ServletRequest request)
    {
        try
        {
            Authentication authentication = _authenticator.validateRequest(request,__deferredResponse,true);

            if (authentication!=null && (authentication instanceof Authentication.User) && !(authentication instanceof Authentication.ResponseSent))
            {
                LoginService login_service= _authenticator.getLoginService();
                IdentityService identity_service=login_service.getIdentityService();
                
                if (identity_service!=null)
                    _previousAssociation=identity_service.associate(((Authentication.User)authentication).getUserIdentity());
                
                return authentication;
            }
        }
        catch (ServerAuthException e)
        {
            LOG.debug(e);
        }

        return this;
    }
