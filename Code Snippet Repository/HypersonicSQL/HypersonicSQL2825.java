    public static Result newCancelRequest(int randomId, long statementId, String sql) {

        Result r = newResult(ResultConstants.SQLCANCEL);

        r.statementID = statementId;
        r.mainString  = sql;
        r.generateKeys = randomId;

        return r;
    }
