    public void cancel() throws SQLException {
        checkClosed();
        String sql = resultOut.getMainString();
        int randomId = connection.sessionProxy.getRandomId();
        Result request = Result.newCancelRequest(randomId, -1, sql);

        try {
            Result response = connection.sessionProxy.cancel(request);
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        }
    }
