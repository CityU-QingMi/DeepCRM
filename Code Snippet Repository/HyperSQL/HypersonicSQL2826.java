    public void setPrepareOrExecuteProperties(String sql, int maxRows,
            int fetchSize, int statementReturnType, int timeout,
            int resultSetProperties, int keyMode, int[] generatedIndexes,
            String[] generatedNames) {

        mainString               = sql;
        updateCount              = maxRows;
        this.fetchSize           = fetchSize;
        this.statementReturnType = statementReturnType;
        queryTimeout             = timeout;
        rsProperties             = resultSetProperties;
        generateKeys             = keyMode;
        generatedMetaData =
            ResultMetaData.newGeneratedColumnsMetaData(generatedIndexes,
                generatedNames);
    }
