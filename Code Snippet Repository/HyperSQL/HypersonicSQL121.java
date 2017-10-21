    OrderedHashSet collectAllExpressions(OrderedHashSet set,
                                         OrderedIntHashSet typeSet,
                                         OrderedIntHashSet stopAtTypeSet) {

        if (stopAtTypeSet.contains(opType)) {
            return set;
        }

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                set = nodes[i].collectAllExpressions(set, typeSet,
                                                     stopAtTypeSet);
            }
        }

        boolean added = false;

        if (typeSet.contains(opType)) {
            if (set == null) {
                set = new OrderedHashSet();
            }

            set.add(this);

            added = true;
        }

        if (!added) {
            if (table != null && table.queryExpression != null) {
                set = table.queryExpression.collectAllExpressions(set,
                        typeSet, stopAtTypeSet);
            }
        }

        return set;
    }
