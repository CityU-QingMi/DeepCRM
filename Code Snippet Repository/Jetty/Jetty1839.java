    @Override
    public AuthStatus validateRequest(final MessageInfo messageInfo, final Subject client, final Subject serviceSubject) throws AuthException
    {

        // Take the request from the messageInfo structure.
        final HttpServletRequest req = (HttpServletRequest)messageInfo.getRequestMessage();
        try
        {
            // Get the user name from the header. If not there then fail authentication.
            final String userName = req.getHeader("X-Forwarded-User");
            if (userName == null)
            {
                return AuthStatus.FAILURE;
            }

            // Store the user name that was in the header and also set a group.
            handler.handle(new Callback[]
            { new CallerPrincipalCallback(client,userName), new GroupPrincipalCallback(client,new String[]
            { "users" }) });
            return AuthStatus.SUCCESS;
        }
        catch (final Exception e)
        {
            throw new AuthException(e.getMessage());
        }
    }
