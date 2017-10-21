    Result executePSMProcedure(Session session) {

        int variableCount = procedure.getVariableCount();
        int cursorCount   = procedure.getCursorCount();

        session.sessionContext.routineVariables = new Object[variableCount];
        session.sessionContext.routineCursors   = new Result[cursorCount];

        Result result = procedure.statement.execute(session);

        if (result.isError()) {
            return result;
        }

        return result;
    }
