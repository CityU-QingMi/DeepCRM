    boolean hasNonDeterministicFunction() {

        OrderedHashSet list = null;

        list = collectAllExpressions(list, Expression.functionExpressionSet,
                                     Expression.emptyExpressionSet);

        if (list == null) {
            return false;
        }

        for (int j = 0; j < list.size(); j++) {
            Expression current = (Expression) list.get(j);

            if (current.opType == OpTypes.FUNCTION) {
                if (!((FunctionSQLInvoked) current).isDeterministic()) {
                    return true;
                }
            } else if (current.opType == OpTypes.SQL_FUNCTION) {
                if (!((FunctionSQL) current).isDeterministic()) {
                    return true;
                }
            }
        }

        return false;
    }
