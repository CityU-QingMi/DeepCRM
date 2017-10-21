    ResultSet getResultSet() throws SQLException {

        checkClosed();

        ResultSet result = currentResultSet;

        if(!connection.isCloseResultSet) {
            currentResultSet = null;
        }

        if (result == null) {

            // if statement has been used with executeQuery and the result is update count
            // return an empty result for 1.8 compatibility
            if (resultOut.getStatementType() == StatementTypes.RETURN_RESULT) {
                return JDBCResultSet.newEmptyResultSet();
            }
        }

        return result;
    }
