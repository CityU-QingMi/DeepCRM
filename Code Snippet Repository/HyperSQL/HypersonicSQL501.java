    void readRoutineSQLBody(Routine routine) {

        startRecording();
        session.sessionContext.pushRoutineTables();

        try {
            Statement statement = compileSQLProcedureStatementOrNull(routine,
                null);

            if (statement == null) {
                throw unexpectedToken();
            }

            Token[] tokenisedStatement = getRecordedStatement();
            String  sql                = Token.getSQL(tokenisedStatement);

            statement.setSQL(sql);
            routine.setProcedure(statement);
        } finally {
            session.sessionContext.popRoutineTables();
        }
    }
