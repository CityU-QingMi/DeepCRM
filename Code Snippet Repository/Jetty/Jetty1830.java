    protected boolean login(Subject clientSubject, String username, 
                            Credential credential, String authMethod, 
                            MessageInfo messageInfo) 
    throws IOException, UnsupportedCallbackException
    {
        CredentialValidationCallback credValidationCallback = new CredentialValidationCallback(clientSubject, username, credential);
        callbackHandler.handle(new Callback[] { credValidationCallback });
        if (credValidationCallback.getResult())
        {
            Set<LoginCallbackImpl> loginCallbacks = clientSubject.getPrivateCredentials(LoginCallbackImpl.class);
            if (!loginCallbacks.isEmpty())
            {
                LoginCallbackImpl loginCallback = loginCallbacks.iterator().next();
                CallerPrincipalCallback callerPrincipalCallback = new CallerPrincipalCallback(clientSubject, loginCallback.getUserPrincipal());
                GroupPrincipalCallback groupPrincipalCallback = new GroupPrincipalCallback(clientSubject, loginCallback.getRoles());
                callbackHandler.handle(new Callback[] { callerPrincipalCallback, groupPrincipalCallback });
            }
            messageInfo.getMap().put(JaspiMessageInfo.AUTH_METHOD_KEY, authMethod);
        }
        return credValidationCallback.getResult();

    }
