    void fetchResult() throws SQLException {

        if (isClosed || connection.isClosed) {
            checkClosed();
        }
        closeResultData();
        checkParametersSet();

        if (isBatch) {
            throw JDBCUtil.sqlExceptionSQL(ErrorCode.X_07505);
        }

        //
        if (isResult) {
            resultOut.setPreparedResultUpdateProperties(parameterValues);
        } else {
            resultOut.setPreparedExecuteProperties(parameterValues, maxRows,
                    fetchSize, rsProperties, queryTimeout);
        }

        try {
            performPreExecute();

            resultIn = session.execute(resultOut);
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        } finally {
            performPostExecute();
        }

        if (resultIn.mode == ResultConstants.ERROR) {
            throw JDBCUtil.sqlException(resultIn);
        }

        if (resultIn.isData()) {
            currentResultSet = new JDBCResultSet(connection, this, resultIn,
                    resultIn.metaData);
        } else if (statementRetType == StatementTypes.RETURN_RESULT) {
            getMoreResults();
        }
    }
