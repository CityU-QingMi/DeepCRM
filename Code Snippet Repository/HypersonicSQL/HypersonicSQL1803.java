    public void end(Xid xid, int flags) throws XAException {

        validateXid(xid);

        if (state != XA_STATE_STARTED) {
            throw new XAException("Invalid XAResource state");
        }

        /** @todo - probably all flags can be ignored */
        if (flags == XAResource.TMSUCCESS) {}

        state = XA_STATE_ENDED;
    }
