    public OrderedHashSet getReferencesForDependents() {

        OrderedHashSet set = new OrderedHashSet();

        for (int i = 0; i < colTypes.length; i++) {
            ColumnSchema   column = getColumn(i);
            OrderedHashSet refs   = column.getReferences();

            if (refs != null && !refs.isEmpty()) {
                set.add(column.getName());
            }
        }

        for (int i = 0; i < fkConstraints.length; i++) {
            if (fkConstraints[i].getMainTableName() != getName()) {
                set.add(fkConstraints[i].getName());
            }
        }

        for (int i = 0; i < triggerList.length; i++) {
            set.add(triggerList[i].getName());
        }

        return set;
    }
