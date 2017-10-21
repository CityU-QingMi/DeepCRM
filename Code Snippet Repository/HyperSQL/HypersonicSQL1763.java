    private void fetchResult(String sql, int statementRetType,
                             int generatedKeys, int[] generatedIndexes,
                             String[] generatedNames) throws SQLException {

        checkClosed();
        closeResultData();

        if (isEscapeProcessing) {
            sql = connection.nativeSQL(sql);
        }
        resultOut.setPrepareOrExecuteProperties(sql, maxRows, fetchSize,
                statementRetType, queryTimeout, rsProperties, generatedKeys,
                generatedIndexes, generatedNames);

        try {
            resultIn = connection.sessionProxy.execute(resultOut);

            performPostExecute();
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        }

        if (resultIn.isError()) {
            throw JDBCUtil.sqlException(resultIn);
        }

        if (resultIn.isData()) {
            currentResultSet = new JDBCResultSet(connection, this, resultIn,
                    resultIn.metaData);
        } else if (resultIn.getStatementType()
                   == StatementTypes.RETURN_RESULT) {
            getMoreResults();
        }
    }
