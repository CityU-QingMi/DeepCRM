    StatementSchema compileCreateTable(int type) {

        boolean  ifNot = readIfNotExists();
        HsqlName name  = readNewSchemaObjectName(SchemaObject.TABLE, false);

        name.setSchemaIfNull(session.getCurrentSchemaHsqlName());

        Table table;

        switch (type) {

            case TableBase.TEMP_TEXT_TABLE :
            case TableBase.TEXT_TABLE : {
                table = new TextTable(database, name, type);

                break;
            }
            default : {
                table = new Table(database, name, type);
            }
        }

        if (token.tokenType == Tokens.AS) {
            return compileCreateTableAsSubqueryDefinition(table);
        }

        return compileCreateTableBody(table, ifNot);
    }
