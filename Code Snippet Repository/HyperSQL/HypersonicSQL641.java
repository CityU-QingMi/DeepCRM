    void assignToLists() {

        int lastOuterIndex = -1;

        for (int i = 0; i < rangeVariables.length; i++) {
            if (rangeVariables[i].isLeftJoin) {
                lastOuterIndex = i;
            }

            if (rangeVariables[i].isRightJoin) {
                lastOuterIndex = i;
            }

            if (lastOuterIndex == i) {
                joinExpressions[i].addAll(tempJoinExpressions[i]);
            } else {
                int start = lastOuterIndex + 1;

                for (int j = 0; j < tempJoinExpressions[i].size(); j++) {
                    Expression e = (Expression) tempJoinExpressions[i].get(j);

                    assignToJoinLists(e, joinExpressions, start);
                }
            }
        }

        for (int i = 0; i < queryConditions.size(); i++) {
            assignToJoinLists((Expression) queryConditions.get(i),
                              whereExpressions, lastRightJoinIndex);
        }
    }
