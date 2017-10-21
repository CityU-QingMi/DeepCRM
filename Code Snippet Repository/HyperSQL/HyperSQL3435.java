    Table readLocalTableVariableDeclarationOrNull(Routine routine) {

        int position = getPosition();

        readThis(Tokens.DECLARE);

        if (token.tokenType == Tokens.TABLE) {
            read();

            HsqlName name = readNewSchemaObjectName(SchemaObject.TABLE, false);

            name.schema = SqlInvariants.MODULE_HSQLNAME;

            Table table = new Table(database, name, TableBase.TEMP_TABLE);

            table.persistenceScope = TableBase.SCOPE_ROUTINE;

            readTableDefinition(routine, table);
            session.sessionContext.addSessionTable(table);

            return table;
        } else {
            rewind(position);

            return null;
        }
    }
