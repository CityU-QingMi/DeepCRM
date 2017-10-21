    Table ADMINISTRABLE_ROLE_AUTHORIZATIONS(Session session,
            PersistentStore store) {

        Table t = sysTables[ADMINISTRABLE_ROLE_AUTHORIZATIONS];

        if (t == null) {
            t = createBlankTable(
                sysTableHsqlNames[ADMINISTRABLE_ROLE_AUTHORIZATIONS]);

            addColumn(t, "GRANTEE", SQL_IDENTIFIER);
            addColumn(t, "ROLE_NAME", SQL_IDENTIFIER);
            addColumn(t, "IS_GRANTABLE", SQL_IDENTIFIER);

            HsqlName name = HsqlNameManager.newInfoSchemaObjectName(
                sysTableHsqlNames[ADMINISTRABLE_ROLE_AUTHORIZATIONS].name,
                false, SchemaObject.INDEX);

            t.createPrimaryKeyConstraint(name, new int[] {
                0, 1, 2
            }, false);

            return t;
        }

        if (session.isAdmin()) {
            insertRoles(session, t, session.getGrantee(), true);
        }

        return t;
    }
