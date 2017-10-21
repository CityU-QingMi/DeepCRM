    public void compile(Session session, SchemaObject parentObject) {

        ParserRoutine p = new ParserRoutine(session,
                                            new Scanner(session,
                                                statement.getSQL()));

        session.sessionContext.pushRoutineTables();

        try {
            p.read();
            p.startRecording();

            Statement statement = p.compileSQLProcedureStatementOrNull(this,
                null);
            Token[] tokenisedStatement = p.getRecordedStatement();
            String  sql                = Token.getSQL(tokenisedStatement);

            statement.setSQL(sql);
            setProcedure(statement);
            statement.resolve(session);
            setReferences();
        } finally {
            session.sessionContext.popRoutineTables();
        }
    }
