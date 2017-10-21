    public synchronized void clearBatch() throws SQLException {

        if (isClosed || connection.isClosed) {
            checkClosed();
        }

        if (isBatch) {
            resultOut.getNavigator().clear();
        }
    }
