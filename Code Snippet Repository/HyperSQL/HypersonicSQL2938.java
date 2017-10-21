    void removeDroppedColumns(OrderedHashSet columnSet, Table table) {

        for (int i = 0; i < columnSet.size(); i++) {
            HsqlName name = (HsqlName) columnSet.get(i);

            if (table.findColumn(name.name) >= 0) {
                columnSet.remove(i);

                i--;
            }
        }
    }
