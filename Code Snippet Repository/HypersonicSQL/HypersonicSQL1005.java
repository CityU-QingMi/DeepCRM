    Result getResult(Session session) {

        Result result = null;

        switch (operationType) {

            case StatementSet.TRIGGER_SET :
                result = executeTriggerSetStatement(session);
                break;

            case StatementSet.SELECT_INTO : {
                Object[] values = queryExpression.getSingleRowValues(session);

                if (values == null) {
                    session.addWarning(HsqlException.noDataCondition);

                    result = Result.updateZeroResult;

                    break;
                }

                result = performAssignment(session, variableIndexes, targets,
                                           values, sourceTypes);

                break;
            }
            case StatementSet.VARIABLE_SET : {
                Object[] values = getExpressionValues(session);

                if (values == null) {
                    result = Result.updateZeroResult;

                    break;
                }

                result = performAssignment(session, variableIndexes, targets,
                                           values, sourceTypes);

                break;
            }
            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "StatementSet");
        }

        return result;
    }
