    void closeResultData() throws SQLException {

        if (currentResultSet != null) {
            currentResultSet.close();
        }

        if (generatedResultSet != null) {
            generatedResultSet.close();
        }

        generatedResultSet = null;
        generatedResult    = null;
        resultIn           = null;
        currentResultSet   = null;
    }
