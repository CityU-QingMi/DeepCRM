    public static ResultMetaData newDoubleColumnMetaData(String colNameA,
            String colNameB) {

        ResultMetaData md = ResultMetaData.newResultMetaData(2);

        md.columns[0] = new ColumnBase(null, null, null, colNameA);

        md.columns[0].setType(Type.SQL_VARCHAR_DEFAULT);

        md.columns[1] = new ColumnBase(null, null, null, colNameB);

        md.columns[1].setType(Type.SQL_VARCHAR_DEFAULT);
        md.prepareData();

        return md;
    }
