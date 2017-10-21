    private HashMappedList getUnionColumns() {

        if (unionCorresponding || leftQueryExpression == null) {
            HashMappedList columns = ((TableDerived) resultTable).columnList;
            HashMappedList list    = new HashMappedList();

            for (int i = 0; i < unionColumnMap.length; i++) {
                ColumnSchema column =
                    (ColumnSchema) columns.get(unionColumnMap[i]);

                list.add(column.getName().name, column);
            }

            return list;
        }

        return leftQueryExpression.getUnionColumns();
    }
