    private Result performPostExecute(Result command, Result result) {

        if (result.mode == ResultConstants.DATA) {
            result = sessionData.getDataResultHead(command, result, isNetwork);
        }

/**/
/**/
/**/
/**/
/**/
/**/
/**/
        if (sqlWarnings != null && sqlWarnings.size() > 0) {
            if (result.mode == ResultConstants.UPDATECOUNT) {
                result = new Result(ResultConstants.UPDATECOUNT,
                                    result.getUpdateCount());
            }

            HsqlException[] warnings = getAndClearWarnings();

            result.addWarnings(warnings);
        }

        return result;
    }
