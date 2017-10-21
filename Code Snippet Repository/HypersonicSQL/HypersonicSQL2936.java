    OrderedHashSet getColumnsForAllRights(Table table) {

        if (isFull) {
            return table.getColumnNameSet();
        }

        if (isFullSelect || isFullInsert || isFullUpdate || isFullReferences) {
            return table.getColumnNameSet();
        }

        OrderedHashSet set = new OrderedHashSet();

        if (selectColumnSet != null) {
            set.addAll(selectColumnSet);
        }

        if (insertColumnSet != null) {
            set.addAll(insertColumnSet);
        }

        if (updateColumnSet != null) {
            set.addAll(updateColumnSet);
        }

        if (referencesColumnSet != null) {
            set.addAll(referencesColumnSet);
        }

        return set;
    }
