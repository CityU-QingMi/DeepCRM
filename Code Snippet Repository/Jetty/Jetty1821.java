    public boolean secureResponse(JaspiMessageInfo messageInfo, Authentication validatedUser) throws ServerAuthException
    {
        try
        {
            String authContextId = _authConfig.getAuthContextID(messageInfo);
            ServerAuthContext authContext = _authConfig.getAuthContext(authContextId, _serviceSubject, _authProperties);
            // TODO
            // authContext.cleanSubject(messageInfo,validatedUser.getUserIdentity().getSubject());
            AuthStatus status = authContext.secureResponse(messageInfo, _serviceSubject);
            return (AuthStatus.SEND_SUCCESS.equals(status));
        }
        catch (AuthException e)
        {
            throw new ServerAuthException(e);
        }
    }
