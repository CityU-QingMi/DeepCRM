    public BlobData getBlob(long lobID) {

        writeLock.lock();

        try {
            Object[] data = getLobHeader(lobID);

            if (data == null) {
                return null;
            }

            BlobData blob = new BlobDataID(lobID);

            return blob;
        } finally {
            writeLock.unlock();
        }
    }
