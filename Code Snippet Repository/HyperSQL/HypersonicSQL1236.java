    synchronized void pushPair(Session session, Object[] oldData,
                               Object[] newData) {

        Result result = Result.updateZeroResult;

        session.sessionContext.push();

        if (rangeVars[OLD_ROW] != null || rangeVars[NEW_ROW] != null) {
            session.sessionContext.triggerArguments = new Object[][] {
                oldData, newData
            };
        }

        if (condition.testCondition(session)) {
            int variableCount = routine.getVariableCount();

            session.sessionContext.routineVariables =
                new Object[variableCount];
            result = routine.statement.execute(session);
        }

        session.sessionContext.pop();

        if (result.isError()) {
            throw result.getException();
        }
    }
