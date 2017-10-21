    private void dispose() throws XAException {

        state = XA_STATE_DISPOSED;

        xaDataSource.removeResource(xid);

        xid = null;

        try {
            connection.setAutoCommit(originalAutoCommitMode);    // real/phys.
        } catch (SQLException se) {
            throw new XAException(se.toString());
        }
    }
