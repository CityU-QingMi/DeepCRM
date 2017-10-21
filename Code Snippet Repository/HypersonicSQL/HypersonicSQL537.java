    private boolean resolveColumnReferences(Session session, Expression e,
            int rangeCount, boolean withSequences) {

        if (e == null) {
            return true;
        }

        int oldSize = unresolvedExpressions == null ? 0
                                                    : unresolvedExpressions
                                                        .size();

        unresolvedExpressions = e.resolveColumnReferences(session, this,
                rangeCount, RangeGroup.emptyArray, unresolvedExpressions,
                withSequences);

        int newSize = unresolvedExpressions == null ? 0
                                                    : unresolvedExpressions
                                                        .size();

        return oldSize == newSize;
    }
