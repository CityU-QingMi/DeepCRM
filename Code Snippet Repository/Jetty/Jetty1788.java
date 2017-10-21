    public void logout(UserIdentity user)
    {
        Set<JAASUserPrincipal> userPrincipals = user.getSubject().getPrincipals(JAASUserPrincipal.class);
        LoginContext loginContext = userPrincipals.iterator().next().getLoginContext();
        try
        {
            loginContext.logout();
        }
        catch (LoginException e)
        {
            LOG.warn(e);
        }
    }
