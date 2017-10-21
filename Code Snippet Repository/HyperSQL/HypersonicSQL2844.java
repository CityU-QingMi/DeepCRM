    public static Result newCallResponse(Type[] types, long statementId,
                                         Object[] values) {

        Result result = newResult(ResultConstants.CALL_RESPONSE);

        result.metaData    = ResultMetaData.newSimpleResultMetaData(types);
        result.statementID = statementId;
        result.valueData   = values;

        return result;
    }
