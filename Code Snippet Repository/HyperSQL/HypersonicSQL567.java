    public void replaceRangeVariables(RangeVariable[] ranges,
                                      RangeVariable[] newRanges) {

        for (int i = 0; i < indexStartAggregates; i++) {
            exprColumns[i].replaceRangeVariables(ranges, newRanges);
        }

        if (queryCondition != null) {
            queryCondition.replaceRangeVariables(ranges, newRanges);
        }

        if (havingCondition != null) {
            havingCondition.replaceRangeVariables(ranges, newRanges);
        }

        for (int i = 0, len = rangeVariables.length; i < len; i++) {
            rangeVariables[i].getSubqueries();
        }
    }
