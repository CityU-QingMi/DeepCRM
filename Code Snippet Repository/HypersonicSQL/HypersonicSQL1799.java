    public void start(Xid xid, int flags) throws XAException {

        // Comment out following debug statement before public release:
/**/
/**/
/**/
        if (state != XA_STATE_INITIAL && state != XA_STATE_DISPOSED
                && state != XA_STATE_ENDED) {
            throw new XAException("Invalid XAResource state");
        }

        if (xaDataSource == null) {
            throw new XAException(
                "JDBCXAResource has not been associated with a XADataSource");
        }

        if (xid == null) {

            // This block asserts that all JDBCXAResources with state
            // >= XA_STATE_STARTED have a non-null xid.
            throw new XAException("Null Xid");
        }

        try {
            if (connection.getAutoCommit()) {
                originalAutoCommitMode = true;      // real/phys.

                connection.setAutoCommit(false);    // real/phys.
            }
        } catch (SQLException se) {
            throw new XAException(se.toString());
        }

        if (!xid.equals(this.xid)) {
            this.xid = xid;

            xaDataSource.addResource(this.xid, this);
        }

        state = XA_STATE_STARTED;

        // N.b.  The DataSource does not have this XAResource in its list
        // until right here.  We can't tell DataSource before our start()
        // method, because we don't know our Xid before now.
    }
