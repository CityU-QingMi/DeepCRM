    Table SYSTEM_TABLETYPES(Session session, PersistentStore store) {

        Table t = sysTables[SYSTEM_TABLETYPES];

        if (t == null) {
            t = createBlankTable(sysTableHsqlNames[SYSTEM_TABLETYPES]);

            addColumn(t, "TABLE_TYPE", SQL_IDENTIFIER);    // not null

            // order: TABLE_TYPE
            // true PK
            HsqlName name = HsqlNameManager.newInfoSchemaObjectName(
                sysTableHsqlNames[SYSTEM_TABLETYPES].name, false,
                SchemaObject.INDEX);

            t.createPrimaryKeyConstraint(name, new int[]{ 0 }, true);

            return t;
        }

        Object[] row;

        for (int i = 0; i < tableTypes.length; i++) {
            row    = t.getEmptyRowData();
            row[0] = tableTypes[i];

            t.insertSys(session, store, row);
        }

        return t;
    }
