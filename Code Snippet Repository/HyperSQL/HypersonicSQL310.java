    private Statement compilePerform() {

        Integer type   = Integer.valueOf(1);
        Integer number = Integer.valueOf(-1);

        read();

        switch (token.tokenType) {

            case Tokens.CHECK : {
                read();

                HsqlName tableName = null;

                readThis(Tokens.TABLE);
                readThis(Tokens.SPACE);

                if (readIfThis(Tokens.AND)) {
                    readThis("FIX");

                    type = Integer.valueOf(2);
                }

                Object[] args = new Object[] {
                    tableName, type, number
                };
                HsqlName[] names =
                    database.schemaManager.getCatalogAndBaseTableNames();

                return new StatementCommand(StatementTypes.CHECK_INDEX, args,
                                            null, names);
            }
            default :
                throw unexpectedToken();
        }
    }
