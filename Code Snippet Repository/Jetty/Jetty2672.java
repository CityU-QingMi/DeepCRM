    @Override
    public Authentication login(String username, Object password, ServletRequest request)
    {
        if (username == null)
            return null;
        
        UserIdentity identity = _authenticator.login(username, password, request);
        if (identity != null)
        {
            IdentityService identity_service = _authenticator.getLoginService().getIdentityService();
            UserAuthentication authentication = new UserAuthentication("API",identity);
            if (identity_service != null)
                _previousAssociation=identity_service.associate(identity);
            return authentication;
        }
        return null;
    }
