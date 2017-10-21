    public int compare(BlobData a, BlobData b) {

        if (a.getId() == b.getId()) {
            return 0;
        }

        writeLock.lock();

        try {
            if (compressLobs || cryptLobs) {
                return compareBytesCompressed(a.getId(), b.getId());
            } else {
                return compareBytesNormal(a.getId(), b.getId());
            }
        } finally {
            writeLock.unlock();
        }
    }
