    public synchronized void addBatch() throws SQLException {

        if (isClosed || connection.isClosed) {
            checkClosed();
        }
        checkParametersSet();

        if (!isBatch) {
            resultOut.setBatchedPreparedExecuteRequest();

            isBatch = true;
        }

        try {
            performPreExecute();
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        }

        int      len              = parameterValues.length;
        Object[] batchParamValues = new Object[len];

        System.arraycopy(parameterValues, 0, batchParamValues, 0, len);
        resultOut.addBatchedPreparedExecuteRequest(batchParamValues);
    }
