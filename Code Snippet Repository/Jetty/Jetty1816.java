    public UserInfo (String userName, Credential credential, List<String> roleNames)
    {
        _userName = userName;
        _credential = credential;
        if (roleNames != null)
        {
            synchronized (_roleNames)
            {
                _roleNames.addAll(roleNames);
                _rolesLoaded = true;
            }
        }
    }
