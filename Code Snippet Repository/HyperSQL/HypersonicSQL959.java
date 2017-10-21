    TableDerived[] getSubqueries(Session session) {

        OrderedHashSet subQueries = null;

        for (int i = 0; i < targetRangeVariables.length; i++) {
            if (targetRangeVariables[i] == null) {
                continue;
            }

            OrderedHashSet set = targetRangeVariables[i].getSubqueries();

            subQueries = OrderedHashSet.addAll(subQueries, set);
        }

        for (int i = 0; i < updateExpressions.length; i++) {
            subQueries = updateExpressions[i].collectAllSubqueries(subQueries);
        }

        if (insertExpression != null) {
            subQueries = insertExpression.collectAllSubqueries(subQueries);
        }

        if (condition != null) {
            subQueries = condition.collectAllSubqueries(subQueries);
        }

        if (queryExpression != null) {
            OrderedHashSet set = queryExpression.getSubqueries();

            subQueries = OrderedHashSet.addAll(subQueries, set);
        }

        if (updatableTableCheck != null) {
            OrderedHashSet set = updatableTableCheck.getSubqueries();

            subQueries = OrderedHashSet.addAll(subQueries, set);
        }

        if (subQueries == null || subQueries.size() == 0) {
            return TableDerived.emptyArray;
        }

        TableDerived[] subQueryArray = new TableDerived[subQueries.size()];

        subQueries.toArray(subQueryArray);

        return subQueryArray;
    }
