    public static Result newDoubleColumnResult(String colNameA,
            String colNameB) {

        Result result = newResult(ResultConstants.DATA);

        result.metaData = ResultMetaData.newDoubleColumnMetaData(colNameA,
                colNameB);
        result.navigator = new RowSetNavigatorClient(8);

        return result;
    }
