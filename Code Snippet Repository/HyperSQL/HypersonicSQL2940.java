    static boolean containsAllColumns(OrderedHashSet columnSet, Table table,
                                      boolean[] columnCheckList) {

        for (int i = 0; i < columnCheckList.length; i++) {
            if (columnCheckList[i]) {
                if (columnSet == null) {
                    return false;
                }

                if (columnSet.contains(table.getColumn(i).getName())) {
                    continue;
                }

                return false;
            }
        }

        return true;
    }
