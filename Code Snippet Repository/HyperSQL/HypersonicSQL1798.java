    public void rollbackThis() throws XAException {

        if (state != XA_STATE_PREPARED && state != XA_STATE_ENDED) {
            throw new XAException("Invalid XAResource state");
        }

        try {

/**/
/**/
/**/
/**/
            connection.rollback();    // real/phys.
        } catch (SQLException se) {
            throw new XAException(se.toString());
        }

        dispose();
    }
