    public static Result newWarningResult(HsqlException w) {

        Result result = newResult(ResultConstants.WARNING);

        result.mainString = w.getMessage();
        result.subString  = w.getSQLState();
        result.errorCode  = w.getErrorCode();

        return result;
    }
