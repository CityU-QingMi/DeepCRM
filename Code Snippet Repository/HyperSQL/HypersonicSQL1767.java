    public synchronized void addBatch(String sql) throws SQLException {

        checkClosed();

        if (isEscapeProcessing) {
            sql = connection.nativeSQL(sql);
        }

        if (batchResultOut == null) {
            batchResultOut = Result.newBatchedExecuteRequest();
        }
        batchResultOut.getNavigator().add(new Object[] { sql });
    }
