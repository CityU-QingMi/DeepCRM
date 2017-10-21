    Xid[] getPreparedXids() {

        lock.writeLock().lock();

        try {

            Iterator it = resources.keySet().iterator();
            Xid curXid;
            HashSet preparedSet = new HashSet();

            while (it.hasNext()) {
                curXid = (Xid) it.next();

                if ( ( (JDBCXAResource) resources.get(curXid)).state
                    == JDBCXAResource.XA_STATE_PREPARED) {
                    preparedSet.add(curXid);
                }
            }

            Xid[] array = new Xid[preparedSet.size()];

            preparedSet.toArray(array);

            return array;
        } finally {
            lock.writeLock().unlock();
        }
    }
