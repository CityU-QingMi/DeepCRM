    void collectTableNamesForRead(OrderedHashSet set) {

        if (expression == null) {
            set.addAll(procedure.getTableNamesForRead());
        } else {
            for (int i = 0; i < subqueries.length; i++) {
                if (subqueries[i].queryExpression != null) {
                    subqueries[i].queryExpression.getBaseTableNames(set);
                }
            }

            for (int i = 0; i < routines.length; i++) {
                set.addAll(routines[i].getTableNamesForRead());
            }
        }
    }
