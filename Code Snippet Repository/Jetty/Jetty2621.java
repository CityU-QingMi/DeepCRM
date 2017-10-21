    @Override
    public boolean validate(UserIdentity user)
    {
        if (!isFullValidate())
            return true; //if we have a user identity it must be valid
        
        //Do a full validation back against the user store     
        UserPrincipal fresh = loadUserInfo(user.getUserPrincipal().getName());
        if (fresh == null)
            return false; //user no longer exists
        
        if (user.getUserPrincipal() instanceof UserPrincipal)
        {
            return fresh.authenticate(((UserPrincipal)user.getUserPrincipal())._credential);
        }
        
        throw new IllegalStateException("UserPrincipal not KnownUser"); //can't validate
    }
