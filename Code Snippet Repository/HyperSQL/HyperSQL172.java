    void dropConstraintsAndIndexes(OrderedHashSet dropConstraintSet,
                                   OrderedHashSet dropIndexSet) {

        Table tn = table.moveDefinition(session, table.tableType, null, null,
                                        null, -1, 0, dropConstraintSet,
                                        dropIndexSet);

        if (tn.indexList.length == table.indexList.length) {
            database.persistentStoreCollection.removeStore(tn);

            return;
        }

        moveData(table, tn, -1, 0);

        table = tn;
    }
