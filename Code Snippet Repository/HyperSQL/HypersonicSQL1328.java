    Table SQL_FEATURES(Session session, PersistentStore store) {

        Table t = sysTables[SQL_FEATURES];

        if (t == null) {
            t = createBlankTable(sysTableHsqlNames[SQL_FEATURES]);

            addColumn(t, "FEATURE_ID", CHARACTER_DATA);
            addColumn(t, "FEATURE_NAME", CHARACTER_DATA);
            addColumn(t, "SUB_FEATURE_ID", CHARACTER_DATA);
            addColumn(t, "SUB_FEATURE_NAME", CHARACTER_DATA);
            addColumn(t, "IS_SUPPORTED", YES_OR_NO);
            addColumn(t, "IS_VERIFIED_BY", CHARACTER_DATA);
            addColumn(t, "COMMENTS", CHARACTER_DATA);

            HsqlName name = HsqlNameManager.newInfoSchemaObjectName(
                sysTableHsqlNames[SQL_FEATURES].name, false,
                SchemaObject.INDEX);

            t.createPrimaryKeyConstraint(name, new int[] {
                0, 2
            }, false);

            return t;
        }

        Session sys = database.sessionManager.newSysSession(
            SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, session.getUser());
        String sql = (String) statementMap.get("/*sql_features*/");
        Result rs  = sys.executeDirectStatement(sql);

        t.insertSys(session, store, rs);

        return t;
    }
