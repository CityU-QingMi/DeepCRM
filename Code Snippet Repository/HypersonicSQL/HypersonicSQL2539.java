    public int compare(Collation collation, ClobData a, ClobData b) {

        if (a.getId() == b.getId()) {
            return 0;
        }

        writeLock.lock();

        try {
            if (compressLobs || cryptLobs) {
                return compareTextCompressed(collation, a.getId(), b.getId());
            } else {
                return compareTextNormal(collation, a.getId(), b.getId());
            }
        } finally {
            writeLock.unlock();
        }
    }
