    private Statement compileCheckpoint() {

        boolean defrag = false;

        read();

        if (token.tokenType == Tokens.DEFRAG) {
            defrag = true;

            read();
        } else if (token.tokenType == Tokens.SEMICOLON) {
            read();

            // only semicolon is accepted here
        }

        if (token.tokenType != Tokens.X_ENDPARSE) {
            throw unexpectedToken();
        }

        HsqlName[] names =
            database.schemaManager.getCatalogAndBaseTableNames();
        Object[] args = new Object[]{ Boolean.valueOf(defrag) };
        Statement cs = new StatementCommand(StatementTypes.DATABASE_CHECKPOINT,
                                            args, null, names);

        return cs;
    }
