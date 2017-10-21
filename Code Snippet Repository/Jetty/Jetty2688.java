    @Override
    public Authentication validateRequest(ServletRequest request, ServletResponse response, boolean mandatory) throws ServerAuthException
    {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;

        String header = req.getHeader(HttpHeader.AUTHORIZATION.asString());
        String authScheme = getAuthSchemeFromHeader(header);

        if (!mandatory)
        {
            return new DeferredAuthentication(this);
        }

        // The client has responded to the challenge we sent previously
        if (header != null && isAuthSchemeNegotiate(authScheme))
        {
            String spnegoToken = header.substring(10);

            UserIdentity user = login(null,spnegoToken, request);

            if ( user != null )
            {
                return new UserAuthentication(getAuthMethod(),user);
            }
        }

        // A challenge should be sent if any of the following cases are true:
        //   1. There was no Authorization header provided
        //   2. There was an Authorization header for a type other than Negotiate
        try
        {
             if (DeferredAuthentication.isDeferred(res))
             {
                 return Authentication.UNAUTHENTICATED;
             }

            LOG.debug("SpengoAuthenticator: sending challenge");
            res.setHeader(HttpHeader.WWW_AUTHENTICATE.asString(), HttpHeader.NEGOTIATE.asString());
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return Authentication.SEND_CONTINUE;
        }
        catch (IOException ioe)
        {
            throw new ServerAuthException(ioe);
        }
    }
