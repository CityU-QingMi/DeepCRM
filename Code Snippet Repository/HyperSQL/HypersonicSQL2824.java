    public static Result newPrepareResponse(Statement statement) {

        Result r = newResult(ResultConstants.PREPARE_ACK);

        r.statement   = statement;
        r.statementID = statement.getID();

        int csType = statement.getType();

        r.statementReturnType = statement.getStatementReturnType();
        r.metaData            = statement.getResultMetaData();
        r.parameterMetaData   = statement.getParametersMetaData();

        return r;
    }
