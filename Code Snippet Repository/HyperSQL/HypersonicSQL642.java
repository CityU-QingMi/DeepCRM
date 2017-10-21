    void assignToJoinLists(Expression e, HsqlList[] expressionLists,
                           int first) {

        if (e == null) {
            return;
        }

        tempSet.clear();
        e.collectRangeVariables(rangeVariables, tempSet);

        int index = rangeVarSet.getLargestIndex(tempSet);

        if (index == -1) {
            index = 0;
        }

        if (index < first) {
            index = first;
        }

        if (e instanceof ExpressionLogical) {
            if (((ExpressionLogical) e).isTerminal) {
                index = expressionLists.length - 1;
            }
        }

        expressionLists[index].add(e);
    }
