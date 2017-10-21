    StatementSchema compileCreateSequence() {

        read();

        Boolean ifNotExists = readIfNotExists();
/**/
/**/
/**/
/**/
/**/
/**/
        HsqlName name = readNewSchemaObjectName(SchemaObject.SEQUENCE, false);
        NumberSequence sequence = new NumberSequence(name, Type.SQL_INTEGER);

        readSequenceOptions(sequence, true, false, false);

        String     sql            = getLastPart();
        Object[]   args           = new Object[] {
            sequence, ifNotExists
        };
        HsqlName[] writeLockNames = database.schemaManager.catalogNameArray;

        return new StatementSchema(sql, StatementTypes.CREATE_SEQUENCE, args,
                                   null, writeLockNames);
    }
