    Table TRANSLATIONS(Session session, PersistentStore store) {

        Table t = sysTables[TRANSLATIONS];

        if (t == null) {
            t = createBlankTable(sysTableHsqlNames[TRANSLATIONS]);

            addColumn(t, "TRANSLATION_CATALOG", SQL_IDENTIFIER);
            addColumn(t, "TRANSLATION_SCHEMA", SQL_IDENTIFIER);
            addColumn(t, "TRANSLATION_NAME", SQL_IDENTIFIER);
            addColumn(t, "SOURCE_CHARACTER_SET_CATALOG", SQL_IDENTIFIER);
            addColumn(t, "SOURCE_CHARACTER_SET_SCHEMA", SQL_IDENTIFIER);
            addColumn(t, "SOURCE_CHARACTER_SET_NAME", SQL_IDENTIFIER);
            addColumn(t, "TARGET_CHARACTER_SET_CATALOG", SQL_IDENTIFIER);
            addColumn(t, "TARGET_CHARACTER_SET_SCHEMA", SQL_IDENTIFIER);
            addColumn(t, "TARGET_CHARACTER_SET_NAME", SQL_IDENTIFIER);
            addColumn(t, "TRANSLATION_SOURCE_CATALOG", SQL_IDENTIFIER);
            addColumn(t, "TRANSLATION_SOURCE_SCHEMA", SQL_IDENTIFIER);
            addColumn(t, "TRANSLATION_SOURCE_NAME", SQL_IDENTIFIER);

            HsqlName name = HsqlNameManager.newInfoSchemaObjectName(
                sysTableHsqlNames[TRANSLATIONS].name, false,
                SchemaObject.INDEX);

            t.createPrimaryKeyConstraint(name, new int[] {
                0, 1, 2
            }, false);

            return t;
        }

        return t;
    }
