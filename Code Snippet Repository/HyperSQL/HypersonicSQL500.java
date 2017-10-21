    private void readTableDefinition(Routine routine, Table table) {

        readThis(Tokens.OPENBRACKET);

        for (int i = 0; ; i++) {
            ColumnSchema newcolumn = readRoutineParameter(routine, false);

            if (newcolumn.getName() == null) {
                throw unexpectedToken();
            }

            table.addColumn(newcolumn);

            if (token.tokenType == Tokens.COMMA) {
                read();
            } else {
                readThis(Tokens.CLOSEBRACKET);

                break;
            }
        }

        table.createPrimaryKey();
    }
