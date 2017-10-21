    static void collectSubQueriesAndReferences(OrderedHashSet set,
            Expression expression) {

        expression.collectAllExpressions(set,
                                         Expression.subqueryExpressionSet,
                                         Expression.emptyExpressionSet);

        int size = set.size();

        for (int i = 0; i < size; i++) {
            Expression e = (Expression) set.get(i);

            e.collectObjectNames(set);
        }
    }
