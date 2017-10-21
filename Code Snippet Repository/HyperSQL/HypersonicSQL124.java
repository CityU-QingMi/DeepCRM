    public void checkValidCheckConstraint() {

        OrderedHashSet set = null;

        set = collectAllExpressions(set,
                                    Expression.subqueryAggregateExpressionSet,
                                    Expression.emptyExpressionSet);

        if (set != null && !set.isEmpty()) {
            throw Error.error(ErrorCode.X_0A000,
                              "subquery in check constraint");
        }
    }
