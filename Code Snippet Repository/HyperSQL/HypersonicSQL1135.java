    static Table newSingleColumnTable(Database database, HsqlName tableName,
                                      int tableType, HsqlName colName,
                                      Type colType) {

        TableDerived table;

        table = new TableDerived(database, tableName, tableType);

        ColumnSchema column = new ColumnSchema(colName, colType, false, true,
                                               null);

        table.addColumn(column);
        table.createPrimaryKeyConstraint(table.getName(), new int[]{ 0 },
                                         true);

        return table;
    }
