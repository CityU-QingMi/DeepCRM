    Table APPLICABLE_ROLES(Session session, PersistentStore store) {

        Table t = sysTables[APPLICABLE_ROLES];

        if (t == null) {
            t = createBlankTable(sysTableHsqlNames[APPLICABLE_ROLES]);

            addColumn(t, "GRANTEE", SQL_IDENTIFIER);
            addColumn(t, "ROLE_NAME", SQL_IDENTIFIER);
            addColumn(t, "IS_GRANTABLE", SQL_IDENTIFIER);

            HsqlName name = HsqlNameManager.newInfoSchemaObjectName(
                sysTableHsqlNames[APPLICABLE_ROLES].name, false,
                SchemaObject.INDEX);

            t.createPrimaryKeyConstraint(name, new int[] {
                0, 1, 2
            }, false);

            return t;
        }

        insertRoles(session, t, session.getGrantee(), session.isAdmin());

        return t;
    }
