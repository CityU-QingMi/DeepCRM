    public Authentication validateRequest(ServletRequest request, ServletResponse response, boolean mandatory) throws ServerAuthException
    {
        JaspiMessageInfo info = new JaspiMessageInfo(request, response, mandatory);
        request.setAttribute("org.eclipse.jetty.security.jaspi.info", info);

        Authentication a = validateRequest(info);
        
        //if its not mandatory to authenticate, and the authenticator returned UNAUTHENTICATED, we treat it as authentication deferred
        if (_allowLazyAuthentication && !info.isAuthMandatory() && a == Authentication.UNAUTHENTICATED)
            a = new DeferredAuthentication(this);
        return a;
    }
