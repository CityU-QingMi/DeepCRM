    StatementSchema compileCreateSynonym(boolean isOrReplace) {

        HsqlName synonymHsqlName;
        HsqlName targetHsqlName;

        read();

        synonymHsqlName = readNewSchemaObjectName(SchemaObject.REFERENCE,
                true);

        readThis(Tokens.FOR);

        targetHsqlName = readNewSchemaObjectName(SchemaObject.REFERENCE, true);

        String   sql  = getLastPart();
        Object[] args = new Object[] {
            synonymHsqlName, targetHsqlName
        };

        return new StatementSchema(sql, StatementTypes.CREATE_REFERENCE, args,
                                   null,
                                   new HsqlName[]{
                                       database.getCatalogName() });
    }
