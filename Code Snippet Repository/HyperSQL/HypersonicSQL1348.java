    final Table SYSTEM_SCHEMAS(Session session, PersistentStore store) {

        Table t = sysTables[SYSTEM_SCHEMAS];

        if (t == null) {
            t = createBlankTable(sysTableHsqlNames[SYSTEM_SCHEMAS]);

            addColumn(t, "TABLE_SCHEM", SQL_IDENTIFIER);    // not null
            addColumn(t, "TABLE_CATALOG", SQL_IDENTIFIER);
            addColumn(t, "IS_DEFAULT", Type.SQL_BOOLEAN);

            // order: TABLE_SCHEM
            // true PK, as rows never have null TABLE_SCHEM
            HsqlName name = HsqlNameManager.newInfoSchemaObjectName(
                sysTableHsqlNames[SYSTEM_SCHEMAS].name, false,
                SchemaObject.INDEX);

            t.createPrimaryKeyConstraint(name, new int[]{ 0 }, true);

            return t;
        }

        Object[] row;

        // Initialization
        String[] schemas = database.schemaManager.getSchemaNamesArray();
        String defschema =
            database.schemaManager.getDefaultSchemaHsqlName().name;

        // Do it.
        for (int i = 0; i < schemas.length; i++) {
            row = t.getEmptyRowData();

            String schema = schemas[i];

            row[0] = schema;
            row[1] = database.getCatalogName().name;
            row[2] = schema.equals(defschema) ? Boolean.TRUE
                                              : Boolean.FALSE;

            t.insertSys(session, store, row);
        }

        return t;
    }
