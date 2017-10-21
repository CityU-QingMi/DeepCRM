    @Override
    protected UserPrincipal loadUserInfo(String userName)
    {
        UserIdentity id = _userStore.getUserIdentity(userName);
        if (id != null)
        {
            return (UserPrincipal)id.getUserPrincipal();
        }
        
        return null;
    }
