    void assignToRangeVariables() {

        for (int i = 0; i < rangeVariables.length; i++) {
            boolean                 hasIndex = false;
            RangeVariableConditions conditions;

            if (i < firstLeftJoinIndex
                    && firstRightJoinIndex == rangeVariables.length) {
                conditions = rangeVariables[i].joinConditions[0];

                joinExpressions[i].addAll(whereExpressions[i]);
                assignToRangeVariable(rangeVariables[i], conditions, i,
                                      joinExpressions[i]);
                assignToRangeVariable(conditions, joinExpressions[i]);
            } else {
                conditions = rangeVariables[i].joinConditions[0];

                assignToRangeVariable(rangeVariables[i], conditions, i,
                                      joinExpressions[i]);

                conditions = rangeVariables[i].joinConditions[0];

                if (conditions.hasIndex()) {
                    hasIndex = true;
                }

                assignToRangeVariable(conditions, joinExpressions[i]);

                conditions = rangeVariables[i].whereConditions[0];

                for (int j = i + 1; j < rangeVariables.length; j++) {
                    if (rangeVariables[j].isRightJoin) {
                        assignToRangeVariable(
                            rangeVariables[j].whereConditions[0],
                            whereExpressions[i]);
                    }
                }

                if (!hasIndex) {
                    assignToRangeVariable(rangeVariables[i], conditions, i,
                                          whereExpressions[i]);
                }

                assignToRangeVariable(conditions, whereExpressions[i]);
            }
        }
    }
