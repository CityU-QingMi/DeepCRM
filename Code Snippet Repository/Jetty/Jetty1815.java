    public UserInfo getUserInfo(String userName) throws Exception
    {
        PropertyUserStore propertyUserStore = _propertyUserStores.get(_filename);
        if (propertyUserStore == null)
            throw new IllegalStateException("PropertyUserStore should never be null here!");
        
        LOG.debug("Checking PropertyUserStore "+_filename+" for "+userName);
        UserIdentity userIdentity = propertyUserStore.getUserIdentity(userName);
        if (userIdentity==null)
            return null;

        //TODO in future versions change the impl of PropertyUserStore so its not
        //storing Subjects etc, just UserInfo
        Set<Principal> principals = userIdentity.getSubject().getPrincipals();

        List<String> roles = new ArrayList<String>();

        for ( Principal principal : principals )
        {
            roles.add( principal.getName() );
        }

        Credential credential = (Credential)userIdentity.getSubject().getPrivateCredentials().iterator().next();
        LOG.debug("Found: " + userName + " in PropertyUserStore "+_filename);
        return new UserInfo(userName, credential, roles);
    }
