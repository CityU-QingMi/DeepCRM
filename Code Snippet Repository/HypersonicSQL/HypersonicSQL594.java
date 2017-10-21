    public OrderedHashSet getSubqueries() {

        OrderedHashSet set = null;

        if (joinCondition != null) {
            set = joinCondition.collectAllSubqueries(set);
        }

        if (rangeTable instanceof TableDerived) {
            QueryExpression queryExpression =
                ((TableDerived) rangeTable).getQueryExpression();

            if (queryExpression == null) {
                Expression dataExpression =
                    ((TableDerived) rangeTable).getDataExpression();

                if (dataExpression != null) {
                    if (set == null) {
                        set = new OrderedHashSet();
                    }

                    OrderedHashSet.addAll(set, dataExpression.getSubqueries());
                }
            } else {
                OrderedHashSet temp = queryExpression.getSubqueries();

                set = OrderedHashSet.addAll(set, temp);
                set = OrderedHashSet.add(set, rangeTable);
            }
        }

        return set;
    }
