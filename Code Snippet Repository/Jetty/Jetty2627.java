    protected void configureRoleInfo (RoleInfo ri, ConstraintMapping mapping)
    { 
        Constraint constraint = mapping.getConstraint();
        boolean forbidden = constraint.isForbidden();
        ri.setForbidden(forbidden);
        
        //set up the data constraint (NOTE: must be done after setForbidden, as it nulls out the data constraint
        //which we need in order to do combining of omissions in prepareConstraintInfo
        UserDataConstraint userDataConstraint = UserDataConstraint.get(mapping.getConstraint().getDataConstraint());
        ri.setUserDataConstraint(userDataConstraint);

        //if forbidden, no point setting up roles
        if (!ri.isForbidden())
        {
            //add in the roles
            boolean checked = mapping.getConstraint().getAuthenticate();
            ri.setChecked(checked);

            if (ri.isChecked())
            {
                if (mapping.getConstraint().isAnyRole())
                {
                    // * means matches any defined role
                    for (String role : _roles)
                        ri.addRole(role);
                    ri.setAnyRole(true);
                }
                else if (mapping.getConstraint().isAnyAuth())
                {
                    //being authenticated is sufficient, not necessary to check roles
                    ri.setAnyAuth(true);
                }
                else
                {   
                    //user must be in one of the named roles
                    String[] newRoles = mapping.getConstraint().getRoles();
                     for (String role : newRoles)
                     {
                         //check role has been defined
                         if (!_roles.contains(role))
                             throw new IllegalArgumentException("Attempt to use undeclared role: " + role + ", known roles: " + _roles);
                        ri.addRole(role);
                     }
                 }
             }
         }
     }
