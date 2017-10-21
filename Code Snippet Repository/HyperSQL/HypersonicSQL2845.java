    public static Result newUpdateResultRequest(Type[] types, long id) {

        Result result = newResult(ResultConstants.UPDATE_RESULT);

        result.metaData  = ResultMetaData.newUpdateResultMetaData(types);
        result.id        = id;
        result.valueData = new Object[]{};

        return result;
    }
