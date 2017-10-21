    private void readObject(ObjectInputStream stream)
        throws IOException, ClassNotFoundException
    {
        stream.defaultReadObject();

        SecurityHandler security=SecurityHandler.getCurrentSecurityHandler();
        if (security==null)
            throw new IllegalStateException("!SecurityHandler");
        LoginService login_service=security.getLoginService();
        if (login_service==null)
            throw new IllegalStateException("!LoginService");

        _userIdentity=login_service.login(_name,_credentials, null);
        LOG.debug("Deserialized and relogged in {}",this);
    }
