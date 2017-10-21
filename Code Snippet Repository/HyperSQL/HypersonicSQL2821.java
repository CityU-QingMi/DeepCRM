    public static Result newUpdateCountResult(ResultMetaData meta, int count) {

        Result result     = newResult(ResultConstants.UPDATECOUNT);
        Result dataResult = newGeneratedDataResult(meta);

        result.updateCount = count;

        result.addChainedResult(dataResult);

        return result;
    }
