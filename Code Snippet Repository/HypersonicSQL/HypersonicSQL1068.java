    public OrderedHashSet getColumnNameSet() {

        OrderedHashSet set = new OrderedHashSet();

        for (int i = 0; i < columnCount; i++) {
            set.add(((ColumnSchema) columnList.get(i)).getName());
        }

        return set;
    }
