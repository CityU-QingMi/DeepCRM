    Table CONSTRAINT_TABLE_USAGE(Session session, PersistentStore store) {

        Table t = sysTables[CONSTRAINT_TABLE_USAGE];

        if (t == null) {
            t = createBlankTable(sysTableHsqlNames[CONSTRAINT_TABLE_USAGE]);

            addColumn(t, "TABLE_CATALOG", SQL_IDENTIFIER);
            addColumn(t, "TABLE_SCHEMA", SQL_IDENTIFIER);
            addColumn(t, "TABLE_NAME", SQL_IDENTIFIER);         // not null
            addColumn(t, "CONSTRAINT_CATALOG", SQL_IDENTIFIER);
            addColumn(t, "CONSTRAINT_SCHEMA", SQL_IDENTIFIER);
            addColumn(t, "CONSTRAINT_NAME", SQL_IDENTIFIER);    // not null

            HsqlName name = HsqlNameManager.newInfoSchemaObjectName(
                sysTableHsqlNames[CONSTRAINT_TABLE_USAGE].name, false,
                SchemaObject.INDEX);

            t.createPrimaryKeyConstraint(name, new int[] {
                0, 1, 2, 3, 4, 5
            }, false);

            return t;
        }

        //
        Session sys = database.sessionManager.newSysSession(
            SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, session.getUser());
        Result rs = sys.executeDirectStatement(
            "select DISTINCT TABLE_CATALOG, TABLE_SCHEMA, TABLE_NAME, "
            + "CONSTRAINT_CATALOG, CONSTRAINT_SCHEMA, CONSTRAINT_NAME "
            + "from INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE");

        t.insertSys(session, store, rs);
        sys.close();

        return t;
    }
