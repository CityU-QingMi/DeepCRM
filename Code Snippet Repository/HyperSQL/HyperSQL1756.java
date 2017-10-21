    public ClobData getClob(long lobID) {

        writeLock.lock();

        try {
            Object[] data = getLobHeader(lobID);

            if (data == null) {
                return null;
            }

            ClobData clob = new ClobDataID(lobID);

            return clob;
        } finally {
            writeLock.unlock();
        }
    }
