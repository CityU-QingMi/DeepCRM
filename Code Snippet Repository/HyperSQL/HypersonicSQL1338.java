    Table ASSERTIONS(Session session, PersistentStore store) {

        Table t = sysTables[ASSERTIONS];

        if (t == null) {
            t = createBlankTable(sysTableHsqlNames[ASSERTIONS]);

            addColumn(t, "CONSTRAINT_CATALOG", SQL_IDENTIFIER);
            addColumn(t, "CONSTRAINT_SCHEMA", SQL_IDENTIFIER);
            addColumn(t, "CONSTRAINT_NAME", SQL_IDENTIFIER);    // not null
            addColumn(t, "IS_DEFERRABLE", YES_OR_NO);
            addColumn(t, "INITIALLY_DEFERRED", YES_OR_NO);

            HsqlName name = HsqlNameManager.newInfoSchemaObjectName(
                sysTableHsqlNames[ASSERTIONS].name, false, SchemaObject.INDEX);

            t.createPrimaryKeyConstraint(name, new int[] {
                0, 1, 2
            }, false);

            return t;
        }

        final int constraint_catalog = 0;
        final int constraint_schema  = 1;
        final int constraint_name    = 2;
        final int is_deferrable      = 3;
        final int initially_deferred = 4;

        return t;
    }
