    private SearchResult findUser(String username) throws NamingException, LoginException
    {
        SearchControls ctls = new SearchControls();
        ctls.setCountLimit(1);
        ctls.setDerefLinkFlag(true);
        ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        String filter = "(&(objectClass={0})({1}={2}))";

        if (LOG.isDebugEnabled())
            LOG.debug("Searching for user " + username + " with filter: \'" + filter + "\'" + " from base dn: " + _userBaseDn);

        Object[] filterArguments = new Object[]{
                _userObjectClass,
                _userIdAttribute,
                username
        };
        NamingEnumeration<SearchResult> results = _rootContext.search(_userBaseDn, filter, filterArguments, ctls);

        if (LOG.isDebugEnabled())
            LOG.debug("Found user?: " + results.hasMoreElements());

        if (!results.hasMoreElements())
        {
            throw new LoginException("User not found.");
        }

        return (SearchResult)results.nextElement();
    }
