    void collectTableNamesForWrite(OrderedHashSet set) {

        // other fk references this :  if constraint trigger action  : other write lock
        if (baseTable.isView()) {
            getTriggerTableNames(set, true);
        } else if (!baseTable.isTemp()) {
            set.add(baseTable.getName());

            if (type == StatementTypes.UPDATE_WHERE
                    || type == StatementTypes.MERGE) {
                if (updateExpressions.length != 0) {
                    baseTable.collectFKWriteLocks(updateColumnMap, set);
                }
            } else if (type == StatementTypes.DELETE_WHERE) {
                baseTable.collectFKWriteLocks(null, set);
            }

            getTriggerTableNames(set, true);
        }
    }
