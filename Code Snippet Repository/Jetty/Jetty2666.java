    public void logout(Authentication.User user)
    {
        LOG.debug("logout {}",user);
        LoginService login_service=getLoginService();
        if (login_service!=null)
        {
            login_service.logout(user.getUserIdentity());
        }

        IdentityService identity_service=getIdentityService();
        if (identity_service!=null)
        {
            // TODO recover previous from threadlocal (or similar)
            Object previous=null;
            identity_service.disassociate(previous);
        }
    }
