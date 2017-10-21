    public Result execute(Session session) {

        Result result;

        if (targetTable != null && session.isReadOnly()
                && !targetTable.isTemp()) {
            HsqlException e = Error.error(ErrorCode.X_25006);

            return Result.newErrorResult(e);
        }

        if (isExplain) {
            return getExplainResult(session);
        }

        try {
            if (subqueries.length > 0) {
                materializeSubQueries(session);
            }

            result = getResult(session);
        } catch (Throwable t) {
            result = Result.newErrorResult(t);

            result.getException().setStatementType(group, type);
        } finally {
            clearStructures(session);
        }

        return result;
    }
