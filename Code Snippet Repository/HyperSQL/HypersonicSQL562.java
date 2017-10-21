    public OrderedHashSet collectOuterColumnExpressions(OrderedHashSet set,
            OrderedHashSet exclude) {

        set = collectAllExpressions(set, Expression.columnExpressionSet,
                                    Expression.subqueryAggregateExpressionSet);

        if (set == null) {
            return null;
        }

        for (int i = set.size() - 1; i >= 0; i--) {
            Expression col = (Expression) set.get(i);

            if (ArrayUtil.find(rangeVariables, col.getRangeVariable()) >= 0) {
                set.remove(i);
            }

            if (exclude.contains(col)) {
                set.remove(i);
            }
        }

        if (set.isEmpty()) {
            set = null;
        }

        return set;
    }
