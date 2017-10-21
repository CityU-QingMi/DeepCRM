    public void setPreparedExecuteProperties(Object[] parameterValues,
            int maxRows, int fetchSize, int resultProps, int timeout) {

        mode              = ResultConstants.EXECUTE;
        valueData         = parameterValues;
        updateCount       = maxRows;
        this.fetchSize    = fetchSize;
        this.rsProperties = resultProps;
        queryTimeout      = timeout;
    }
