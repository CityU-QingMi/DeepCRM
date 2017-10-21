    Expression replaceExpressions(OrderedHashSet expressions,
                                  int resultRangePosition) {

        if (opType == OpTypes.VALUE) {
            return this;
        }

        if (opType == OpTypes.SIMPLE_COLUMN) {
            return this;
        }

        Expression exp = (Expression) expressions.get(this);

        if (exp != null) {
            Expression col = new ExpressionColumn(this,
                                                  exp.resultTableColumnIndex,
                                                  resultRangePosition);

            return col;
        }

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == null) {
                continue;
            }

            nodes[i] = nodes[i].replaceExpressions(expressions,
                                                   resultRangePosition);
        }

        if (table != null) {
            if (table.queryExpression != null) {
                table.queryExpression.replaceExpressions(expressions,
                        resultRangePosition);
            }
        }

        return this;
    }
