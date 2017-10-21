    @Override
    protected boolean checkWebResourcePermissions(String pathInContext, Request request, Response response, Object constraintInfo, UserIdentity userIdentity)
            throws IOException
    {
        if (constraintInfo == null)
        {
            return true;
        }
        RoleInfo roleInfo = (RoleInfo)constraintInfo;

        if (!roleInfo.isChecked())
        {
            return true;
        }

        //handle ** role constraint
        if (roleInfo.isAnyAuth() &&  request.getUserPrincipal() != null)
        {
            return true;
        }
        
        //check if user is any of the allowed roles
        boolean isUserInRole = false;
        for (String role : roleInfo.getRoles())
        {
            if (userIdentity.isUserInRole(role, null))
            {
                isUserInRole = true;
                break;
            }
        }
        
        //handle * role constraint
        if (roleInfo.isAnyRole() && request.getUserPrincipal() != null && isUserInRole)
        {
            return true;
        }

        //normal role check
        if (isUserInRole)
        {
            return true;
        }
       
        return false;
    }
