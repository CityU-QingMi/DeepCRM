    public void commitThis(boolean onePhase) throws XAException {

        if (onePhase && state == XA_STATE_PREPARED) {
            throw new XAException(
                "Transaction is in a 2-phase state when 1-phase is requested");
        }

        if ((!onePhase) && state != XA_STATE_PREPARED) {
            throw new XAException("Attempt to do a 2-phase commit when "
                                  + "transaction is not prepared");
        }

        //if (!onePhase) {
        //  throw new XAException(
        //   "Sorry.  HSQLDB has not implemented 2-phase commits yet");
        //}
        try {

/**/
/**/
/**/
/**/
/**/
/**/
            connection.commit();
        } catch (SQLException se) {
            throw new XAException(se.toString());
        }

        dispose();
    }
