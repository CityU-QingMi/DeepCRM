    public void forget(Xid xid) throws XAException {

/**/
/**/
/**/
/**/
/**/
/**/
        validateXid(xid);

        if (state != XA_STATE_PREPARED) {
            throw new XAException(
                "Attempted to forget a XAResource that "
                + "is not in a heuristically completed state");
        }

        dispose();

        state = XA_STATE_INITIAL;
    }
