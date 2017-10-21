    private Boolean testInCondition(Session session) {

        Object[] data = nodes[LEFT].getRowValue(session);

        if (data == null) {
            return null;
        }

        if (Expression.countNulls(data) != 0) {
            return null;
        }

        if (nodes[RIGHT].opType == OpTypes.VALUELIST) {
            final int length = nodes[RIGHT].nodes.length;

            for (int i = 0; i < length; i++) {
                Object[] rowData = nodes[RIGHT].nodes[i].getRowValue(session);

                if (Boolean.TRUE.equals(compareValues(session, data,
                                                      rowData))) {
                    return Boolean.TRUE;
                }
            }

            return Boolean.FALSE;
        }

        throw Error.runtimeError(ErrorCode.U_S0500, "ExpressionLogical");
    }
