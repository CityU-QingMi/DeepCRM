    Table ROLE_COLUMN_GRANTS(Session session, PersistentStore store) {

        Table t = sysTables[ROLE_COLUMN_GRANTS];

        if (t == null) {
            t = createBlankTable(sysTableHsqlNames[ROLE_COLUMN_GRANTS]);

            addColumn(t, "GRANTOR", SQL_IDENTIFIER);           // not null
            addColumn(t, "GRANTEE", SQL_IDENTIFIER);           // not null
            addColumn(t, "TABLE_CATALOG", SQL_IDENTIFIER);
            addColumn(t, "TABLE_SCHEMA", SQL_IDENTIFIER);
            addColumn(t, "TABLE_NAME", SQL_IDENTIFIER);        // not null
            addColumn(t, "COLUMN_NAME", SQL_IDENTIFIER);       // not null
            addColumn(t, "PRIVILEGE_TYPE", CHARACTER_DATA);    // not null
            addColumn(t, "IS_GRANTABLE", YES_OR_NO);           // not null

            // order: COLUMN_NAME, PRIVILEGE
            // for unique: GRANTEE, GRANTOR, TABLE_NAME, TABLE_SCHEMA, TABLE_CAT
            // false PK, as TABLE_SCHEMA and/or TABLE_CAT may be null
            HsqlName name = HsqlNameManager.newInfoSchemaObjectName(
                sysTableHsqlNames[ROLE_COLUMN_GRANTS].name, false,
                SchemaObject.INDEX);

            t.createPrimaryKeyConstraint(name, new int[] {
                5, 6, 1, 0, 4, 3, 2
            }, false);

            return t;
        }

        Session sys = database.sessionManager.newSysSession(
            SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, session.getUser());
        Result rs = sys.executeDirectStatement(
            "SELECT C.GRANTOR, C.GRANTEE, C.TABLE_CATALOG, C.TABLE_SCHEMA, C.TABLE_NAME, C.COLUMN_NAME, C.PRIVILEGE_TYPE, C.IS_GRANTABLE "
            + "FROM INFORMATION_SCHEMA.COLUMN_PRIVILEGES C "
            + "JOIN INFORMATION_SCHEMA.APPLICABLE_ROLES ON C.GRANTEE = ROLE_NAME;");

        t.insertSys(session, store, rs);
        sys.close();

        return t;
    }
