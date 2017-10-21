    public static void addAutoColumns(Table table, Type[] colTypes) {

        for (int i = 0; i < colTypes.length; i++) {
            ColumnSchema column =
                new ColumnSchema(HsqlNameManager.getAutoColumnName(i),
                                 colTypes[i], true, false, null);

            table.addColumnNoCheck(column);
        }
    }
