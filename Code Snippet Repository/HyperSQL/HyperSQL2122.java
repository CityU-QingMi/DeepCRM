    public GranteeManager(Database database) {

        this.database = database;

//        map.add(systemAuthorisation.getNameString(), systemAuthorisation);
//        roleMap.add(systemAuthorisation.getNameString(), systemAuthorisation);
        addRole(
            this.database.nameManager.newHsqlName(
                SqlInvariants.PUBLIC_ROLE_NAME, false, SchemaObject.GRANTEE));

        publicRole          = getRole(SqlInvariants.PUBLIC_ROLE_NAME);
        publicRole.isPublic = true;

        addRole(
            this.database.nameManager.newHsqlName(
                SqlInvariants.DBA_ADMIN_ROLE_NAME, false,
                SchemaObject.GRANTEE));

        dbaRole = getRole(SqlInvariants.DBA_ADMIN_ROLE_NAME);

        dbaRole.setAdminDirect();
        addRole(
            this.database.nameManager.newHsqlName(
                SqlInvariants.SCHEMA_CREATE_ROLE_NAME, false,
                SchemaObject.GRANTEE));

        schemaRole = getRole(SqlInvariants.SCHEMA_CREATE_ROLE_NAME);

        addRole(
            this.database.nameManager.newHsqlName(
                SqlInvariants.CHANGE_AUTH_ROLE_NAME, false,
                SchemaObject.GRANTEE));

        changeAuthRole = getRole(SqlInvariants.CHANGE_AUTH_ROLE_NAME);
    }
