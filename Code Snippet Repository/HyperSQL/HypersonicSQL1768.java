    public synchronized int[] executeBatch() throws SQLException {

        checkClosed();

        generatedResult = null;

        if (batchResultOut == null) {
            batchResultOut = Result.newBatchedExecuteRequest();
        }

        int batchCount = batchResultOut.getNavigator().getSize();

        try {
            resultIn = connection.sessionProxy.execute(batchResultOut);

            performPostExecute();
        } catch (HsqlException e) {
            batchResultOut.getNavigator().clear();

            throw JDBCUtil.sqlException(e);
        }
        batchResultOut.getNavigator().clear();

        if (resultIn.isError()) {
            throw JDBCUtil.sqlException(resultIn);
        }

        RowSetNavigator navigator    = resultIn.getNavigator();
        int[]           updateCounts = new int[navigator.getSize()];

        for (int i = 0; navigator.next(); i++) {
            Object[] data = navigator.getCurrent();

            updateCounts[i] = ((Integer) data[0]).intValue();
        }

        if (updateCounts.length != batchCount) {
            if (errorResult == null) {
                throw new BatchUpdateException(updateCounts);
            } else {
                throw new BatchUpdateException(errorResult.getMainString(),
                        errorResult.getSubString(),
                        errorResult.getErrorCode(), updateCounts);
            }
        }

        return updateCounts;
    }
