    public boolean isUserInRole(String role, Scope scope)
    {
        //Servlet Spec 3.1, pg 125
        if ("*".equals(role))
            return false;
        
        String roleToTest = null;
        if (scope!=null && scope.getRoleRefMap()!=null)
            roleToTest=scope.getRoleRefMap().get(role);

        //Servlet Spec 3.1, pg 125
        if (roleToTest == null)
            roleToTest = role;
       
        for (String r :_roles)
            if (r.equals(roleToTest))
                return true;
        return false;
    }
