    Routine compileTriggerRoutine(Table table, RangeVariable[] ranges,
                                  int beforeOrAfter, int operation) {

        int impact = (beforeOrAfter == TriggerDef.BEFORE) ? Routine.READS_SQL
                                                          : Routine
                                                              .MODIFIES_SQL;
        Routine routine = new Routine(table, ranges, impact, beforeOrAfter,
                                      operation);

        session.sessionContext.pushRoutineTables();

        try {
            startRecording();

            StatementCompound parent =
                new StatementCompound(StatementTypes.BEGIN_END, null, null);

            parent.rangeVariables = ranges;

            Statement statement = compileSQLProcedureStatementOrNull(routine,
                null);

            if (statement == null) {
                throw unexpectedToken();
            }

            Token[] tokenisedStatement = getRecordedStatement();
            String  sql                = Token.getSQL(tokenisedStatement);

            statement.setSQL(sql);
            routine.setProcedure(statement);
            routine.resolve(session);
        } finally {
            session.sessionContext.popRoutineTables();
        }

        return routine;
    }
