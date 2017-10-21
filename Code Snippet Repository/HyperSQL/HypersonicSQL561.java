    public OrderedHashSet getSubqueries() {

        OrderedHashSet set = null;

        for (int i = 0; i < indexLimitExpressions; i++) {
            set = exprColumns[i].collectAllSubqueries(set);
        }

        if (queryCondition != null) {
            set = queryCondition.collectAllSubqueries(set);
        }

        if (havingCondition != null) {
            set = havingCondition.collectAllSubqueries(set);
        }

        for (int i = 0; i < rangeVariables.length; i++) {
            OrderedHashSet temp = rangeVariables[i].getSubqueries();

            set = OrderedHashSet.addAll(set, temp);
        }

        return set;
    }
