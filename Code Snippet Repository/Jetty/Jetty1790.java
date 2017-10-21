    public boolean checkRole (String roleName, Principal runAsRole, Group roles)
    {
        //check if this user has had any temporary role pushed onto
        //them. If so, then only check if the user has that role.
        if (runAsRole != null)
        {
            return (roleName.equals(runAsRole.getName()));
        }
        else
        {
            if (roles == null)
                return false;
            Enumeration<? extends Principal> rolesEnum = roles.members();
            boolean found = false;
            while (rolesEnum.hasMoreElements() && !found)
            {
                Principal p = (Principal)rolesEnum.nextElement();
                found = roleName.equals(p.getName());
            }
            return found;
        }
        
    }
