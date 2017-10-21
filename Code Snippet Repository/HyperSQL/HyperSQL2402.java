    private Statement compileScript() {

        String name = null;

        read();

        if (token.tokenType == Tokens.X_VALUE) {
            name = readQuotedString();
        }

        HsqlName[] names =
            database.schemaManager.getCatalogAndBaseTableNames();
        Object[] args = new Object[]{ name };

        return new StatementCommand(StatementTypes.DATABASE_SCRIPT, args,
                                    null, names);
    }
