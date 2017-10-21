    public int prepareThis() throws XAException {

/**/
/**/
/**/
/**/

/**/
/**/
/**/
/**/
/**/
/**/
/**/
        if (state != XA_STATE_ENDED) {
            throw new XAException("Invalid XAResource state");
        }

        try {
            connection.getSession().prepareCommit();
        } catch (HsqlException e) {
            state = XA_STATE_PREPARED;    // ??? didn't prepare

            throw new XAException(e.getMessage());
        }

        state = XA_STATE_PREPARED;

        return XA_OK;    // As noted above, should check non-committed work.
    }
