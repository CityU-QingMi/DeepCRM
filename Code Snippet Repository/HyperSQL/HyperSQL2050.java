    public static Result newPreparedExecuteRequest(Type[] types,
            long statementId) {

        Result result = newResult(ResultConstants.EXECUTE);

        result.metaData    = ResultMetaData.newSimpleResultMetaData(types);
        result.statementID = statementId;
        result.valueData   = ValuePool.emptyObjectArray;

        return result;
    }
