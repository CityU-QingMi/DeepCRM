    @Override
    public void addRole(String role)
    {
        //add to list of declared roles
        boolean modified = _roles.add(role);
        if (isStarted() && modified)
        {
            // Add the new role to currently defined any role role infos
            for (Map<String,RoleInfo> map : _constraintMap.values())
            {
                for (RoleInfo info : map.values())
                {
                    if (info.isAnyRole())
                        info.addRole(role);
                }
            }
        }
    }
