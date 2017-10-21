    private void validateXid(Xid xid) throws XAException {

        if (xid == null) {
            throw new XAException("Null Xid");
        }

        if (this.xid == null) {
            throw new XAException(
                "There is no live transaction for this XAResource");
        }

        if (!xid.equals(this.xid)) {
            throw new XAException(
                "Given Xid is not that associated with this XAResource object");
        }
    }
