    @Override
    public boolean isUserInRole(Scope scope, String role)
    {
        String roleToTest = null;
        if (scope!=null && scope.getRoleRefMap()!=null)
            roleToTest=scope.getRoleRefMap().get(role);
        if (roleToTest==null)
            roleToTest=role;
        //Servlet Spec 3.1 pg 125 if testing special role **
        if ("**".equals(roleToTest.trim()))
        {
            //if ** is NOT a declared role name, the we return true 
            //as the user is authenticated. If ** HAS been declared as a
            //role name, then we have to check if the user has that role
            if (!declaredRolesContains("**"))
                return true;
            else
                return _userIdentity.isUserInRole(role, scope);
        }
      
        return _userIdentity.isUserInRole(role, scope);
    }
