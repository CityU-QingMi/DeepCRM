    public void initialize(Subject subject,
            CallbackHandler callbackHandler,
            Map<String,?> sharedState,
            Map<String,?> options)
    {
        super.initialize(subject, callbackHandler, sharedState, options);

        //get the user credential query out of the options
        dbUserTable = (String)options.get("userTable");
        dbUserTableUserField = (String)options.get("userField");
        dbUserTableCredentialField = (String)options.get("credentialField");

        userQuery = "select "+dbUserTableCredentialField+" from "+dbUserTable+" where "+dbUserTableUserField+"=?";


        //get the user roles query out of the options
        dbUserRoleTable = (String)options.get("userRoleTable");
        dbUserRoleTableUserField = (String)options.get("userRoleUserField");
        dbUserRoleTableRoleField = (String)options.get("userRoleRoleField");

        rolesQuery = "select "+dbUserRoleTableRoleField+" from "+dbUserRoleTable+" where "+dbUserRoleTableUserField+"=?";

        if(LOG.isDebugEnabled())LOG.debug("userQuery = "+userQuery);
        if(LOG.isDebugEnabled())LOG.debug("rolesQuery = "+rolesQuery);
    }
