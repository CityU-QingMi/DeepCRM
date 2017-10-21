    public synchronized void rollback(boolean chain) {

        resultOut.setAsTransactionEndRequest(ResultConstants.TX_ROLLBACK,
                                             null);

        Result in = execute(resultOut);

        if (in.isError()) {
            throw Error.error(in);
        }
    }
