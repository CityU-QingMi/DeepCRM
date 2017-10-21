    OrderedHashSet collectAllSubqueries(OrderedHashSet set) {

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                set = nodes[i].collectAllSubqueries(set);
            }
        }

        if (table != null) {
            OrderedHashSet tempSet = null;

            if (table.queryExpression != null) {
                tempSet = table.queryExpression.getSubqueries();
                set     = OrderedHashSet.addAll(set, tempSet);
            }

            if (set == null) {
                set = new OrderedHashSet();
            }

            set.add(table);
        }

        return set;
    }
