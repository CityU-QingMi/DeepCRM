    public void createFirstUser(String username, String password) {

        boolean isQuoted = true;

        if (username.equalsIgnoreCase("SA")) {
            username = "SA";
            isQuoted = false;
        }

        HsqlName name =
            granteeManager.database.nameManager.newHsqlName(username,
                isQuoted, SchemaObject.GRANTEE);
        User user = createUser(null, name, password, false);

        user.isLocalOnly = true;

        granteeManager.grant(name.name, SqlInvariants.DBA_ADMIN_ROLE_NAME,
                             granteeManager.getDBARole());
    }
