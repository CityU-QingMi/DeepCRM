    public OrderedHashSet getColumnNameSet(boolean[] columnCheckList) {

        OrderedHashSet set = new OrderedHashSet();

        for (int i = 0; i < columnCheckList.length; i++) {
            if (columnCheckList[i]) {
                set.add(columnList.get(i));
            }
        }

        return set;
    }
