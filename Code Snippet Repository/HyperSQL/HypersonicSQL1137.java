    public static void setColumnsInSchemaTable(Table table,
            HsqlName[] columnNames, Type[] columnTypes) {

        for (int i = 0; i < columnNames.length; i++) {
            HsqlName columnName = columnNames[i];

            columnName = table.database.nameManager.newColumnSchemaHsqlName(
                table.getName(), columnName);

            ColumnSchema column = new ColumnSchema(columnName, columnTypes[i],
                                                   true, false, null);

            table.addColumn(column);
        }

        table.setColumnStructures();
    }
