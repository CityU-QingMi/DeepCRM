    public static JDBCResultSet newEmptyResultSet() {
        ResultMetaData md = ResultMetaData.newResultMetaData(1);

        ColumnBase column =
            new ColumnBase(null, null, null, "C1");
        column.setType(Type.SQL_INTEGER);
        md.columnTypes[0] = Type.SQL_INTEGER;

        md.columns[0] = column;

        Result r = Result.newSingleColumnResult(md);

        return newJDBCResultSet(r, md);
    }
