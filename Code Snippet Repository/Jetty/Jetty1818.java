    public JaspiAuthenticator(ServerAuthConfig authConfig, Map authProperties, ServletCallbackHandler callbackHandler, Subject serviceSubject,
                              boolean allowLazyAuthentication, IdentityService identityService)
    {
        // TODO maybe pass this in via setConfiguration ?
        if (callbackHandler == null) throw new NullPointerException("No CallbackHandler");
        if (authConfig == null) throw new NullPointerException("No AuthConfig");
        this._authConfig = authConfig;
        this._authProperties = authProperties;
        this._callbackHandler = callbackHandler;
        this._serviceSubject = serviceSubject;
        this._allowLazyAuthentication = allowLazyAuthentication;
        this._identityService = identityService;
    }
