    Object[] getExpressionValues(Session session) {

        Object[] values;

        if (expression.getType() == OpTypes.ROW) {
            values = expression.getRowValue(session);
        } else if (expression.getType() == OpTypes.ROW_SUBQUERY) {
            values =
                expression.table.queryExpression.getSingleRowValues(session);

            if (values == null) {

                // todo - verify semantics
                return null;
            }
        } else {
            values    = new Object[1];
            values[0] = expression.getValue(session);
        }

        return values;
    }
