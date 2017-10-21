    Table DATA_TYPE_PRIVILEGES(Session session, PersistentStore store) {

        Table t = sysTables[DATA_TYPE_PRIVILEGES];

        if (t == null) {
            t = createBlankTable(sysTableHsqlNames[DATA_TYPE_PRIVILEGES]);

            addColumn(t, "OBJECT_CATALOG", SQL_IDENTIFIER);
            addColumn(t, "OBJECT_SCHEMA", SQL_IDENTIFIER);
            addColumn(t, "OBJECT_NAME", SQL_IDENTIFIER);    // not null
            addColumn(t, "OBJECT_TYPE", SQL_IDENTIFIER);
            addColumn(t, "DTD_IDENTIFIER", SQL_IDENTIFIER);

            HsqlName name = HsqlNameManager.newInfoSchemaObjectName(
                sysTableHsqlNames[DATA_TYPE_PRIVILEGES].name, false,
                SchemaObject.INDEX);

            t.createPrimaryKeyConstraint(name, new int[] {
                0, 1, 2, 3, 4
            }, false);

            return t;
        }

        //
        Session sys = database.sessionManager.newSysSession(
            SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, session.getUser());
        String sql = (String) statementMap.get("/*data_type_privileges*/");
        Result rs  = sys.executeDirectStatement(sql);

        t.insertSys(session, store, rs);
        sys.close();

        return t;
    }
