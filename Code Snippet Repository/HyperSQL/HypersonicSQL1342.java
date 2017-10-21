    Table ENABLED_ROLES(Session session, PersistentStore store) {

        Table t = sysTables[ENABLED_ROLES];

        if (t == null) {
            t = createBlankTable(sysTableHsqlNames[ENABLED_ROLES]);

            addColumn(t, "ROLE_NAME", SQL_IDENTIFIER);

            // true PK
            HsqlName name = HsqlNameManager.newInfoSchemaObjectName(
                sysTableHsqlNames[ENABLED_ROLES].name, false,
                SchemaObject.INDEX);

            t.createPrimaryKeyConstraint(name, new int[]{ 0 }, true);

            return t;
        }

        // Intermediate holders
        Iterator grantees;
        Grantee  grantee;
        Object[] row;

        // initialization
        grantees = session.getGrantee().getAllRoles().iterator();

        while (grantees.hasNext()) {
            grantee = (Grantee) grantees.next();
            row     = t.getEmptyRowData();
            row[0]  = grantee.getName().getNameString();

            t.insertSys(session, store, row);
        }

        return t;
    }
