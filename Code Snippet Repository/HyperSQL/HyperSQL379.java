    Table AUTHORIZATIONS(Session session, PersistentStore store) {

        Table t = sysTables[AUTHORIZATIONS];

        if (t == null) {
            t = createBlankTable(sysTableHsqlNames[AUTHORIZATIONS]);

            addColumn(t, "AUTHORIZATION_NAME", SQL_IDENTIFIER);    // not null
            addColumn(t, "AUTHORIZATION_TYPE", SQL_IDENTIFIER);    // not null

            // true PK
            HsqlName name = HsqlNameManager.newInfoSchemaObjectName(
                sysTableHsqlNames[AUTHORIZATIONS].name, false,
                SchemaObject.INDEX);

            t.createPrimaryKeyConstraint(name, new int[]{ 0 }, true);

            return t;
        }

        // Intermediate holders
        Iterator grantees;
        Grantee  grantee;
        Object[] row;

        // initialization
        grantees = session.getGrantee().visibleGrantees().iterator();

        // Do it.
        while (grantees.hasNext()) {
            grantee = (Grantee) grantees.next();
            row     = t.getEmptyRowData();
            row[0]  = grantee.getName().getNameString();
            row[1]  = grantee.isRole() ? "ROLE"
                                       : "USER";

            t.insertSys(session, store, row);
        }

        return t;
    }
