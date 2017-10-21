    Statement compileAlterSequence() {

        read();

        HsqlName schema = session.getSchemaHsqlName(token.namePrefix);
        NumberSequence sequence =
            database.schemaManager.getSequence(token.tokenString, schema.name,
                                               true);

        read();

        if (token.tokenType == Tokens.RENAME) {
            read();
            readThis(Tokens.TO);

            return compileRenameObject(sequence.getName(),
                                       SchemaObject.SEQUENCE);
        }

        checkSchemaUpdateAuthorisation(session, sequence.getName().schema);

        NumberSequence copy = sequence.duplicate();

        readSequenceOptions(copy, false, true, false);

        String   sql  = getLastPart();
        Object[] args = new Object[] {
            sequence, copy
        };
        HsqlName[] writeLockNames =
            database.schemaManager.getCatalogNameArray();

        return new StatementSchema(sql, StatementTypes.ALTER_SEQUENCE, args,
                                   null, writeLockNames);
    }
