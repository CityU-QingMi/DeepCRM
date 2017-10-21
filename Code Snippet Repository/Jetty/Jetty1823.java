    protected String findServerName(Server server, Subject subject)
    {
        if (_serverName!=null)
            return _serverName;
        if (subject!=null)
        {
            Set<Principal> principals = subject.getPrincipals();
            if (principals!=null && !principals.isEmpty())
                return principals.iterator().next().getName();
        }
        
        return "server";
    }
