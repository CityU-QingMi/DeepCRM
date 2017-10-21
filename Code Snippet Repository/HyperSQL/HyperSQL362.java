    Table JARS(Session session, PersistentStore store) {

        Table t = sysTables[JARS];

        if (t == null) {
            t = createBlankTable(sysTableHsqlNames[JARS]);

            addColumn(t, "JAR_CATALOG", SQL_IDENTIFIER);
            addColumn(t, "JAR_SCHEMA", SQL_IDENTIFIER);
            addColumn(t, "JAR_NAME", SQL_IDENTIFIER);
            addColumn(t, "JAR_PATH", CHARACTER_DATA);

            HsqlName name = HsqlNameManager.newInfoSchemaObjectName(
                sysTableHsqlNames[JARS].name, false, SchemaObject.INDEX);

            t.createPrimaryKeyConstraint(name, new int[] {
                0, 1, 2, 3
            }, false);

            return t;
        }

        // column number mappings
        final int jar_catalog = 0;
        final int jar_schema  = 1;
        final int jar_name    = 2;
        final int jar_path    = 3;

        //
        Iterator it;
        Object[] row;

        return t;
    }
