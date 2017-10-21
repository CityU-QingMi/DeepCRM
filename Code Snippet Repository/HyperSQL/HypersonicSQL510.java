    public boolean areColumnsResolved() {

        if (unresolvedExpressions == null || unresolvedExpressions.isEmpty()) {
            return true;
        }

        for (int i = 0; i < unresolvedExpressions.size(); i++) {
            Expression e = (Expression) unresolvedExpressions.get(i);

            if (e.getRangeVariable() == null) {
                return false;
            }

            if (e.getRangeVariable().rangeType == RangeVariable.TABLE_RANGE) {
                return false;
            }
        }

        return true;
    }
