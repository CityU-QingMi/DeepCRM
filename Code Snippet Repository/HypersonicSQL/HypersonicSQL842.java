    private Result executeResultUpdate(Result cmd) {

        long   id         = cmd.getResultId();
        int    actionType = cmd.getActionType();
        Result result     = sessionData.getDataResult(id);

        if (result == null) {
            return Result.newErrorResult(Error.error(ErrorCode.X_24501));
        }

        Object[]       pvals     = (Object[]) cmd.valueData;
        Type[]         types     = cmd.metaData.columnTypes;
        StatementQuery statement = (StatementQuery) result.getStatement();

        sessionContext.rowUpdateStatement.setRowActionProperties(result,
                actionType, statement, types);

        Result resultOut =
            executeCompiledStatement(sessionContext.rowUpdateStatement, pvals,
                                     cmd.queryTimeout);

        return resultOut;
    }
