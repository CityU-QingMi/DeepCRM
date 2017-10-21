    private void createResultMetaData(Session session) {

        resultMetaData = ResultMetaData.newResultMetaData(resultColumnTypes,
                columnMap, indexLimitVisible, indexLimitRowId);

        for (int i = 0; i < indexLimitVisible; i++) {
            Expression   e           = exprColumns[i];
            ColumnSchema tableColumn = null;
            ColumnBase   column;

            tableColumn                   = e.getColumn();
            resultMetaData.columnTypes[i] = e.getDataType();

            if (tableColumn == null) {
                column = new ColumnBase();
            } else {
                column = new ColumnBase(session.database.getCatalogName().name,
                                        tableColumn);
            }

            column.setType(e.getDataType());

            resultMetaData.columns[i]      = column;
            resultMetaData.columnLabels[i] = e.getAlias();
        }
    }
