    private Result newColumnResult(long position,
                                   int count) throws SQLException {

        if (!JDBCClobClient.isInLimits(data.length, position, count)) {
            throw JDBCUtil.outOfRangeArgument();
        }

        Type[] types = new Type[2];

        types[0] = Type.SQL_INTEGER;
        types[1] = elementType;

        ResultMetaData meta = ResultMetaData.newSimpleResultMetaData(types);

        meta.columnLabels = new String[] {
            "C1", "C2"
        };
        meta.colIndexes   = new int[] {
            -1, -1
        };
        meta.columns      = new ColumnBase[2];

        ColumnBase column = new ColumnBase("", "", "", "");

        column.setType(types[0]);

        meta.columns[0] = column;
        column          = new ColumnBase("", "", "", "");

        column.setType(types[1]);

        meta.columns[1] = column;

        RowSetNavigatorClient navigator = new RowSetNavigatorClient();

        for (int i = (int) position; i < position + count; i++) {
            Object[] rowData = new Object[2];

            rowData[0] = Integer.valueOf(i + 1);
            rowData[1] = data[i];

            navigator.add(rowData);
        }

        Result result = Result.newDataResult(meta);

        result.setNavigator(navigator);

        return result;
    }
