    public static ResultMetaData newSingleColumnMetaData(String colName) {

        ResultMetaData md = ResultMetaData.newResultMetaData(1);

        md.columns[0] = new ColumnBase(null, null, null, colName);

        md.columns[0].setType(Type.SQL_VARCHAR_DEFAULT);
        md.prepareData();

        return md;
    }
