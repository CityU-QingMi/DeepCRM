    public Authenticator getAuthenticator(Server server, ServletContext context, AuthConfiguration configuration, IdentityService identityService, LoginService loginService)
    {
        String auth=configuration.getAuthMethod();
        Authenticator authenticator=null;
        
        if (auth==null || Constraint.__BASIC_AUTH.equalsIgnoreCase(auth))
            authenticator=new BasicAuthenticator();
        else if (Constraint.__DIGEST_AUTH.equalsIgnoreCase(auth))
            authenticator=new DigestAuthenticator();
        else if (Constraint.__FORM_AUTH.equalsIgnoreCase(auth))
            authenticator=new FormAuthenticator();
        else if ( Constraint.__SPNEGO_AUTH.equalsIgnoreCase(auth) )
            authenticator = new SpnegoAuthenticator();
        else if ( Constraint.__NEGOTIATE_AUTH.equalsIgnoreCase(auth) ) // see Bug #377076
            authenticator = new SpnegoAuthenticator(Constraint.__NEGOTIATE_AUTH);
        if (Constraint.__CERT_AUTH.equalsIgnoreCase(auth)||Constraint.__CERT_AUTH2.equalsIgnoreCase(auth))
            authenticator=new ClientCertAuthenticator();
        
        return authenticator;
    }
