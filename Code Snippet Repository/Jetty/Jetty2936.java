    @Override
    public void login(String username, String password) throws ServletException
    {
        if (_authentication instanceof Authentication.Deferred)
        {
            _authentication=((Authentication.Deferred)_authentication).login(username,password,this);
            if (_authentication == null)
                throw new Authentication.Failed("Authentication failed for username '"+username+"'");
        }
        else
        {
            throw new Authentication.Failed("Authenticated failed for username '"+username+"'. Already authenticated as "+_authentication);
        }
    }
