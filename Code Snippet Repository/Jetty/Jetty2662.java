    public void combine(RoleInfo other)
    {
        if (other._forbidden)
            setForbidden(true);
        else if (!other._checked) // TODO is this the right way around???
            setChecked(true);
        else if (other._isAnyRole)
            setAnyRole(true);
        else if (other._isAnyAuth)
            setAnyAuth(true);
        else if (!_isAnyRole)
        {
            for (String r : other._roles)
                _roles.add(r);
        }
        
        setUserDataConstraint(other._userDataConstraint);
    }
