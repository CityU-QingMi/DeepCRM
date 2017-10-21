    private static Result newTwoColumnResult() {

        Type[] types = new Type[2];

        types[0] = Type.SQL_BIGINT;
        types[1] = Type.SQL_VARBINARY_DEFAULT;

        ResultMetaData  meta = ResultMetaData.newSimpleResultMetaData(types);
        RowSetNavigator navigator = new RowSetNavigatorClient();
        Result          result    = Result.newDataResult(meta);

        result.setNavigator(navigator);

        return result;
    }
