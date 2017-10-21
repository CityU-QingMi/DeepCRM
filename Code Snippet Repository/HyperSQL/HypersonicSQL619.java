        private void replaceExpressions(OrderedHashSet expressions,
                                        int resultRangePosition) {

            if (indexCond != null) {
                for (int i = 0; i < indexCond.length; i++) {
                    if (indexCond[i] != null) {
                        indexCond[i] = indexCond[i].replaceExpressions(
                            expressions, resultRangePosition);
                    }
                }
            }

            if (indexEndCond != null) {
                for (int i = 0; i < indexEndCond.length; i++) {
                    if (indexEndCond[i] != null) {
                        indexEndCond[i] = indexEndCond[i].replaceExpressions(
                            expressions, resultRangePosition);
                    }
                }
            }

            if (indexEndCondition != null) {
                indexEndCondition =
                    indexEndCondition.replaceExpressions(expressions,
                        resultRangePosition);
            }

            if (excludeConditions != null) {
                excludeConditions =
                    excludeConditions.replaceExpressions(expressions,
                        resultRangePosition);
            }

            if (nonIndexCondition != null) {
                nonIndexCondition =
                    nonIndexCondition.replaceExpressions(expressions,
                        resultRangePosition);
            }

            if (terminalCondition != null) {
                terminalCondition =
                    terminalCondition.replaceExpressions(expressions,
                        resultRangePosition);
            }
        }
