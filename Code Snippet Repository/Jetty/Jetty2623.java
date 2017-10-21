    public boolean declaredRolesContains(String roleName)
    {
        SecurityHandler security=SecurityHandler.getCurrentSecurityHandler();
        if (security==null)
            return false;
        
        if (security instanceof ConstraintAware)
        {
            Set<String> declaredRoles = ((ConstraintAware)security).getRoles();
            return (declaredRoles != null) && declaredRoles.contains(roleName);
        }
        
        return false;
    }
