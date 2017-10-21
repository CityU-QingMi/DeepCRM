    public synchronized void close() throws SQLException {

        if (isClosed) {
            return;
        }
        closeResultData();

        batchResultOut = null;
        connection     = null;
        resultIn       = null;
        resultOut      = null;
        isClosed       = true;
    }
