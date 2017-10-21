    final Table INFORMATION_SCHEMA_CATALOG_NAME(Session session,
            PersistentStore store) {

        Table t = sysTables[INFORMATION_SCHEMA_CATALOG_NAME];

        if (t == null) {
            t = createBlankTable(
                sysTableHsqlNames[INFORMATION_SCHEMA_CATALOG_NAME]);

            addColumn(t, "CATALOG_NAME", SQL_IDENTIFIER);    // not null

            // order:  TABLE_CAT
            // true PK
            HsqlName name = HsqlNameManager.newInfoSchemaObjectName(
                sysTableHsqlNames[INFORMATION_SCHEMA_CATALOG_NAME].name,
                false, SchemaObject.INDEX);

            t.createPrimaryKeyConstraint(name, new int[]{ 0 }, true);

            return t;
        }

        Object[] row = t.getEmptyRowData();

        row[0] = database.getCatalogName().name;

        t.insertSys(session, store, row);

        return t;
    }
