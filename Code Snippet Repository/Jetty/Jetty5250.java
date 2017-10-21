    public void setRoles(String[] roles)
    {
        _roles = roles;
        _anyRole = false;
        _anyAuth = false;
        if (roles != null) 
        {
            for (int i = roles.length; i-- > 0;)
            {
                _anyRole |= ANY_ROLE.equals(roles[i]);
                _anyAuth |= ANY_AUTH.equals(roles[i]);
            }
        }
    }
