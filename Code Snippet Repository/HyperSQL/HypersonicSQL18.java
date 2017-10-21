    public synchronized void releaseSavepoint(String name) {

        resultOut.setAsTransactionEndRequest(
            ResultConstants.TX_SAVEPOINT_NAME_RELEASE, name);

        Result in = execute(resultOut);

        if (in.isError()) {
            throw Error.error(in);
        }
    }
