    void assignToRangeVariable(RangeVariable rangeVar,
                               RangeVariableConditions conditions,
                               int rangeVarIndex, HsqlList exprList) {

        if (exprList.isEmpty()) {
            return;
        }

        setIndexConditions(conditions, exprList, rangeVarIndex, true);
    }
