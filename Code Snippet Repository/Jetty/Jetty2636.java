    public static Constraint createConstraint (String name, String[] rolesAllowed, EmptyRoleSemantic permitOrDeny, TransportGuarantee transport)
    {
        Constraint constraint = createConstraint();
        
        if (rolesAllowed == null || rolesAllowed.length==0)
        {           
            if (permitOrDeny.equals(EmptyRoleSemantic.DENY))
            {
                //Equivalent to <auth-constraint> with no roles
                constraint.setName(name+"-Deny");
                constraint.setAuthenticate(true);
            }
            else
            {
                //Equivalent to no <auth-constraint>
                constraint.setName(name+"-Permit");
                constraint.setAuthenticate(false);
            }
        }
        else
        {
            //Equivalent to <auth-constraint> with list of <security-role-name>s
            constraint.setAuthenticate(true);
            constraint.setRoles(rolesAllowed);
            constraint.setName(name+"-RolesAllowed");           
        } 

        //Equivalent to //<user-data-constraint><transport-guarantee>CONFIDENTIAL</transport-guarantee></user-data-constraint>
        constraint.setDataConstraint((transport.equals(TransportGuarantee.CONFIDENTIAL)?Constraint.DC_CONFIDENTIAL:Constraint.DC_NONE));
        return constraint; 
    }
