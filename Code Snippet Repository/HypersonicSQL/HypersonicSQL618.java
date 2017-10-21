        private void replaceColumnReferences(RangeVariable range,
                                             Expression[] list) {

            if (indexCond != null) {
                for (int i = 0; i < indexCond.length; i++) {
                    if (indexCond[i] != null) {
                        indexCond[i] =
                            indexCond[i].replaceColumnReferences(range, list);
                    }
                }
            }

            if (indexEndCond != null) {
                for (int i = 0; i < indexEndCond.length; i++) {
                    if (indexEndCond[i] != null) {
                        indexEndCond[i] =
                            indexEndCond[i].replaceColumnReferences(range,
                                list);
                    }
                }
            }

            if (indexEndCondition != null) {
                indexEndCondition =
                    indexEndCondition.replaceColumnReferences(range, list);
            }

            if (excludeConditions != null) {
                excludeConditions =
                    excludeConditions.replaceColumnReferences(range, list);
            }

            if (nonIndexCondition != null) {
                nonIndexCondition =
                    nonIndexCondition.replaceColumnReferences(range, list);
            }

            if (terminalCondition != null) {
                terminalCondition =
                    terminalCondition.replaceColumnReferences(range, list);
            }
        }
