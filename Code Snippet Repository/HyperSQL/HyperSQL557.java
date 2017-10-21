    public synchronized void commit(boolean chain) {

        resultOut.setAsTransactionEndRequest(ResultConstants.TX_COMMIT, null);

        Result in = execute(resultOut);

        if (in.isError()) {
            throw Error.error(in);
        }
    }
