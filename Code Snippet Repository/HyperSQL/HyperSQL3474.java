    public OrderedHashSet getSubqueries() {

        OrderedHashSet subqueries = leftQueryExpression.getSubqueries();

        subqueries =
            OrderedHashSet.addAll(subqueries,
                                  rightQueryExpression.getSubqueries());

        return subqueries;
    }
