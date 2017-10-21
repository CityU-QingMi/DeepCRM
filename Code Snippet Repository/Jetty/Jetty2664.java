    protected void doStop() throws Exception
    {
        //if we discovered the services (rather than had them explicitly configured), remove them.
        if (!isManaged(_identityService))
        {
            removeBean(_identityService);
            _identityService = null;   
        }
        
        if (!isManaged(_loginService))
        {
            removeBean(_loginService);
            _loginService=null;
        }
        
        super.doStop();
    }
