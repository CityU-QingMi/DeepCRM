    public synchronized void close() {

        if (isClosed) {
            return;
        }

        try {
            resultOut.setResultType(ResultConstants.DISCONNECT);
            execute(resultOut);
        } catch (Exception e) {}

        try {
            closeConnection();
        } catch (Exception e) {}

        isClosed = true;
    }
