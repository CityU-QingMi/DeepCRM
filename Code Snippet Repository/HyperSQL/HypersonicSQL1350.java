    Table SYSTEM_VERSIONCOLUMNS(Session session, PersistentStore store) {

        Table t = sysTables[SYSTEM_VERSIONCOLUMNS];

        if (t == null) {
            t = createBlankTable(sysTableHsqlNames[SYSTEM_VERSIONCOLUMNS]);

            // ----------------------------------------------------------------
            // required by DatabaseMetaData.getVersionColumns result set
            // ----------------------------------------------------------------
            addColumn(t, "SCOPE", Type.SQL_INTEGER);
            addColumn(t, "COLUMN_NAME", SQL_IDENTIFIER);         // not null
            addColumn(t, "DATA_TYPE", Type.SQL_SMALLINT);        // not null
            addColumn(t, "TYPE_NAME", SQL_IDENTIFIER);           // not null
            addColumn(t, "COLUMN_SIZE", Type.SQL_SMALLINT);
            addColumn(t, "BUFFER_LENGTH", Type.SQL_INTEGER);
            addColumn(t, "DECIMAL_DIGITS", Type.SQL_SMALLINT);
            addColumn(t, "PSEUDO_COLUMN", Type.SQL_SMALLINT);    // not null

            // -----------------------------------------------------------------
            // required by DatabaseMetaData.getVersionColumns filter parameters
            // -----------------------------------------------------------------
            addColumn(t, "TABLE_CAT", SQL_IDENTIFIER);
            addColumn(t, "TABLE_SCHEM", SQL_IDENTIFIER);
            addColumn(t, "TABLE_NAME", SQL_IDENTIFIER);          // not null

            // -----------------------------------------------------------------
            HsqlName name = HsqlNameManager.newInfoSchemaObjectName(
                sysTableHsqlNames[SYSTEM_VERSIONCOLUMNS].name, false,
                SchemaObject.INDEX);

            t.createPrimaryKeyConstraint(name, null, false);

            return t;
        }

        return t;
    }
