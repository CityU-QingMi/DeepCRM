    protected LoginService findLoginService() throws Exception
    {
        Collection<LoginService> list = getServer().getBeans(LoginService.class);
        LoginService service = null;
        String realm=getRealmName();
        if (realm!=null)
        {
            for (LoginService s : list)
                if (s.getName()!=null && s.getName().equals(realm))
                {
                    service=s;
                    break;
                }
        }
        else if (list.size()==1)
            service =  list.iterator().next();
        
        return service;
    }
