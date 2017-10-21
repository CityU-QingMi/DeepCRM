    public void commit(Xid xid, boolean onePhase) throws XAException {

        // Comment out following debug statement before public release:
/**/
/**/
/**/
/**/
/**/
        JDBCXAResource resource = xaDataSource.getResource(xid);

        if (resource == null) {
            throw new XAException("The XADataSource has no such Xid:  " + xid);
        }

        resource.commitThis(onePhase);
    }
