        private boolean addToIndexEndConditions(Expression e) {

            if (opType == OpTypes.EQUAL || opType == OpTypes.IS_NULL) {
                if (indexedColumnCount < rangeIndex.getColumnCount()) {
                    if (rangeIndex.getColumns()[indexedColumnCount]
                            == e.getLeftNode().getColumnIndex()) {
                        Expression condition =
                            ExpressionLogical.newNotNullCondition(
                                e.getLeftNode());

                        indexCond[indexedColumnCount]    = condition;
                        indexEndCond[indexedColumnCount] = e;
                        indexEndCondition =
                            ExpressionLogical.andExpressions(indexEndCondition,
                                                             e);
                        opType                         = OpTypes.NOT;
                        opTypes[indexedColumnCount]    = OpTypes.NOT;
                        opTypeEnd                      = e.opType;
                        opTypesEnd[indexedColumnCount] = e.opType;

                        indexedColumnCount++;

                        return true;
                    }
                }
            }

            return false;
        }
