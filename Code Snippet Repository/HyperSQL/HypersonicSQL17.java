    public synchronized void rollbackToSavepoint(String name) {

        resultOut.setAsTransactionEndRequest(
            ResultConstants.TX_SAVEPOINT_NAME_ROLLBACK, name);

        Result in = execute(resultOut);

        if (in.isError()) {
            throw Error.error(in);
        }
    }
