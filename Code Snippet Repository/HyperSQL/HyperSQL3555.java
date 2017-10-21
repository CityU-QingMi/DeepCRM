    public OrderedHashSet getColumnNames() {

        if (columnNames == null) {
            columnNames = new OrderedHashSet();

            rangeTable.getColumnNames(this.usedColumns, columnNames);
        }

        return columnNames;
    }
