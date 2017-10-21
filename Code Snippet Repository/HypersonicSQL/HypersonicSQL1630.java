    public synchronized void close() throws SQLException {

        if (isClosed()) {
            return;
        }
        closeResultData();

        HsqlException he = null;

        try {

            // fredt - if this is called by Connection.close() then there's no
            // need to free the prepared statements on the server - it is done
            // by Connection.close()
            if (!connection.isClosed) {
                session.execute(Result.newFreeStmtRequest(statementID));
            }
        } catch (HsqlException e) {
            he = e;
        }
        parameterValues   = null;
        parameterSet      = null;
        parameterTypes    = null;
        parameterModes    = null;
        resultMetaData    = null;
        parameterMetaData = null;
        resultSetMetaData = null;
        pmd               = null;
        connection        = null;
        session           = null;
        resultIn          = null;
        resultOut         = null;
        isClosed          = true;

        if (he != null) {
            throw JDBCUtil.sqlException(he);
        }
    }
