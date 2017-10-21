    Table SQL_PACKAGES(Session session, PersistentStore store) {

        Table t = sysTables[SQL_PACKAGES];

        if (t == null) {
            t = createBlankTable(sysTableHsqlNames[SQL_PACKAGES]);

            addColumn(t, "ID", CHARACTER_DATA);
            addColumn(t, "NAME", CHARACTER_DATA);
            addColumn(t, "IS_SUPPORTED", YES_OR_NO);
            addColumn(t, "IS_VERIFIED_BY", CHARACTER_DATA);
            addColumn(t, "COMMENTS", CHARACTER_DATA);

            HsqlName name = HsqlNameManager.newInfoSchemaObjectName(
                sysTableHsqlNames[SQL_PACKAGES].name, false,
                SchemaObject.INDEX);

            t.createPrimaryKeyConstraint(name, new int[]{ 0 }, false);

            return t;
        }

        Session sys = database.sessionManager.newSysSession(
            SqlInvariants.INFORMATION_SCHEMA_HSQLNAME, session.getUser());
        String sql = (String) statementMap.get("/*sql_packages*/");
        Result rs  = sys.executeDirectStatement(sql);

        t.insertSys(session, store, rs);

        return t;
    }
