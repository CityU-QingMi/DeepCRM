    Table readTableName(boolean orSynonym) {

        checkIsIdentifier();

        lastSynonym = null;

        Table table = database.schemaManager.findTable(session,
            token.tokenString, token.namePrefix, token.namePrePrefix);

        if (table == null) {
            boolean trySynonym = orSynonym && token.namePrefix == null
                                 && !isViewDefinition;

            if (trySynonym) {
                ReferenceObject reference = database.schemaManager.findSynonym(
                    token.tokenString,
                    session.getCurrentSchemaHsqlName().name,
                    SchemaObject.TABLE);

                if (reference != null) {
                    table = (Table) database.schemaManager.getSchemaObject(
                        reference.getTarget());
                    lastSynonym = reference.getName();
                }
            }

            if (table == null) {
                throw Error.error(ErrorCode.X_42501, token.tokenString);
            }
        }

        getRecordedToken().setExpression(table);
        read();

        return table;
    }
