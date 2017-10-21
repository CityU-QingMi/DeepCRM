    public boolean bindingLogin(String username, Object password) throws LoginException, NamingException
    {
        SearchResult searchResult = findUser(username);

        String userDn = searchResult.getNameInNamespace();

        LOG.info("Attempting authentication: " + userDn);

        Hashtable<Object, Object> environment = getEnvironment();

        if (userDn == null || "".equals(userDn))
        {
            throw new NamingException("username may not be empty");
        }
        environment.put(Context.SECURITY_PRINCIPAL, userDn);
        // RFC 4513 section 6.3.1, protect against ldap server implementations that allow successful binding on empty passwords
        if (password == null || "".equals(password))
        {
            throw new NamingException("password may not be empty");
        }
        environment.put(Context.SECURITY_CREDENTIALS, password);

        DirContext dirContext = new InitialDirContext(environment);
        List<String> roles = getUserRolesByDn(dirContext, userDn);

        UserInfo userInfo = new UserInfo(username, null, roles);
        setCurrentUser(new JAASUserInfo(userInfo));
        setAuthenticated(true);

        return true;
    }
