    public int prepare(Xid xid) throws XAException {

        JDBCXAResource resource = xaDataSource.getResource(xid);

        if (resource == null) {
            throw new XAException("The XADataSource has no such Xid:  " + xid);
        }

        return resource.prepareThis();
    }
