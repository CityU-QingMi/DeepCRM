    boolean getMoreResults(int current) throws SQLException {

        checkClosed();

        if (resultIn == null) {
            return false;
        }

        resultIn = resultIn.getChainedResult();

        if (currentResultSet != null) {
            if( current != KEEP_CURRENT_RESULT) {
                currentResultSet.close();
            }
        }

        currentResultSet = null;

        if (resultIn != null) {
            currentResultSet = new JDBCResultSet(connection, this, resultIn,
                                                 resultIn.metaData);

            return true;
        }

        return false;
    }
