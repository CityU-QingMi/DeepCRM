    public synchronized void close() throws SQLException {

        // Changed to synchronized above because
        // we would not want a sessionProxy.close()
        // operation to occur concurrently with a
        // statementXXX.executeXXX operation.
        if (isInternal || isClosed) {
            return;
        }
        isClosed       = true;
        rootWarning    = null;
        connProperties = null;

        if (isPooled) {
            if (poolEventListener != null) {
                poolEventListener.connectionClosed();

                poolEventListener = null;
            }
        } else if (sessionProxy != null) {
            sessionProxy.close();

            sessionProxy = null;
        }
    }
