    public void setSource(Session session, String text) {

        scanner.reset(session, text);

        statementType = ScriptReaderBase.ANY_STATEMENT;

        scanner.scanNext();

        String s = scanner.getString();

        if (s.equals(Tokens.T_INSERT)) {
            statementType = ScriptReaderBase.INSERT_STATEMENT;

            scanner.scanNext();
            scanner.scanNext();

            tableName = scanner.getString();

            scanner.scanNext();
        } else if (s.equals(Tokens.T_DELETE)) {
            statementType = ScriptReaderBase.DELETE_STATEMENT;

            scanner.scanNext();
            scanner.scanNext();

            tableName = scanner.getString();
        } else if (s.equals(Tokens.T_COMMIT)) {
            statementType = ScriptReaderBase.COMMIT_STATEMENT;
        } else if (s.equals(Tokens.T_SET)) {
            scanner.scanNext();

            if (Tokens.T_SCHEMA.equals(scanner.getString())) {
                scanner.scanNext();

                schemaName    = scanner.getString();
                statementType = ScriptReaderBase.SET_SCHEMA_STATEMENT;
            }
        }
    }
