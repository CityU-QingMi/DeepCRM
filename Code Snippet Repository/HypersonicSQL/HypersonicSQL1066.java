    public OrderedHashSet getColumnNameSet(int[] columnIndexes) {

        OrderedHashSet set = new OrderedHashSet();

        for (int i = 0; i < columnIndexes.length; i++) {
            set.add(((ColumnSchema) columnList.get(i)).getName());
        }

        return set;
    }
