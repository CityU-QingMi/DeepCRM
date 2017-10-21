    public synchronized void prepareCommit() {

        resultOut.setAsTransactionEndRequest(ResultConstants.PREPARECOMMIT,
                                             null);

        Result in = execute(resultOut);

        if (in.isError()) {
            throw Error.error(in);
        }
    }
