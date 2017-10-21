    ResultSet getGeneratedResultSet() throws SQLException {
        checkClosed();
        if (generatedResultSet != null) {
            generatedResultSet.close();
        }

        if (generatedResult == null) {
            generatedResult = Result.emptyGeneratedResult;
        }

        generatedResultSet = new JDBCResultSet(connection, null,
                                               generatedResult,
                                               generatedResult.metaData);

        return generatedResultSet;
    }
