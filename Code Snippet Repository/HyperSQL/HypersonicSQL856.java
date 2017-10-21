    public Result cancel(Result result) {

        if (result.getType() == ResultConstants.SQLCANCEL) {
            if (result.getSessionRandomID() == randomId) {
                database.txManager.resetSession(
                    null, this, TransactionManager.resetSessionAbort);
            }
        }

        return Result.updateZeroResult;
    }
