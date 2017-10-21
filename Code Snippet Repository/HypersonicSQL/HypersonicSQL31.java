    public synchronized RowSetNavigatorClient getRows(long navigatorId,
            int offset, int size) {

        try {
            resultOut.setResultType(ResultConstants.REQUESTDATA);
            resultOut.setResultId(navigatorId);
            resultOut.setUpdateCount(offset);
            resultOut.setFetchSize(size);

            Result result = execute(resultOut);

            return (RowSetNavigatorClient) result.getNavigator();
        } catch (Throwable e) {
            throw Error.error(ErrorCode.X_08006, e.toString());
        }
    }
