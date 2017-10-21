    void reorder() {

        if (!reorder) {
            return;
        }

        if (rangeVariables.length == 1
                || firstRightJoinIndex != rangeVariables.length) {
            return;
        }

        if (firstLeftJoinIndex == 1) {
            return;
        }

        if (firstLateralJoinIndex != rangeVariables.length) {
            return;
        }

        if (sortAndSlice.usingIndex
                && sortAndSlice.primaryTableIndex != null) {
            return;
        }

        HsqlArrayList joins  = new HsqlArrayList();
        HsqlArrayList starts = new HsqlArrayList();

        for (int i = 0; i < firstLeftJoinIndex; i++) {
            HsqlArrayList tempJoins = tempJoinExpressions[i];

            for (int j = 0; j < tempJoins.size(); j++) {
                Expression e = (Expression) tempJoins.get(j);

                if (e.isColumnEqual) {
                    joins.add(e);
                } else if (e.isSingleColumnCondition) {
                    starts.add(e);
                }
            }
        }

        reorderRanges(starts, joins);
    }
