    Table JAR_JAR_USAGE(Session session, PersistentStore store) {

        Table t = sysTables[JAR_JAR_USAGE];

        if (t == null) {
            t = createBlankTable(sysTableHsqlNames[JAR_JAR_USAGE]);

            addColumn(t, "PATH_JAR_CATALOG", SQL_IDENTIFIER);
            addColumn(t, "PATH_JAR_SCHAMA", SQL_IDENTIFIER);
            addColumn(t, "PATH_JAR_NAME", SQL_IDENTIFIER);
            addColumn(t, "JAR_CATALOG", SQL_IDENTIFIER);
            addColumn(t, "JAR_SCHEMA", SQL_IDENTIFIER);
            addColumn(t, "JAR_NAME", SQL_IDENTIFIER);

            HsqlName name = HsqlNameManager.newInfoSchemaObjectName(
                sysTableHsqlNames[JAR_JAR_USAGE].name, false,
                SchemaObject.INDEX);

            t.createPrimaryKeyConstraint(name, new int[] {
                0, 1, 2, 3, 4, 5
            }, false);

            return t;
        }

        // column number mappings
        final int path_jar_catalog = 0;
        final int path_jar_schema  = 1;
        final int path_jar_name    = 2;
        final int jar_catalog      = 3;
        final int jar_schema       = 4;
        final int jar_name         = 5;

        //
        Iterator it;
        Object[] row;

        return t;
    }
