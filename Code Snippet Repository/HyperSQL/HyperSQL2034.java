    public static Result newRequestDataResult(long id, int offset, int count) {

        Result result = newResult(ResultConstants.REQUESTDATA);

        result.id          = id;
        result.updateCount = offset;
        result.fetchSize   = count;

        return result;
    }
