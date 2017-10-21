    Result getResult(Session session) {

        Result result = null;
        int    limit  = Integer.MAX_VALUE;

        if (sortAndSlice != null) {
            int[] limits = sortAndSlice.getLimits(session, null,
                                                  Integer.MAX_VALUE);

            limit = limits[1];
        }

        switch (type) {

            case StatementTypes.UPDATE_WHERE :
                result = executeUpdateStatement(session, limit);
                break;

            case StatementTypes.MERGE :
                result = executeMergeStatement(session);
                break;

            case StatementTypes.DELETE_WHERE :
                if (isTruncate) {
                    result = executeDeleteTruncateStatement(session);
                } else {
                    result = executeDeleteStatement(session, limit);
                }
                break;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "StatementDML");
        }

        session.sessionContext
            .diagnosticsVariables[ExpressionColumn.idx_row_count] =
                Integer.valueOf(result.getUpdateCount());

        return result;
    }
