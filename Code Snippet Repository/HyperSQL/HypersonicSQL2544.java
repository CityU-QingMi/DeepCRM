    public Result getBytes(long lobID, long offset, int length) {

        writeLock.lock();

        try {
            if (compressLobs || cryptLobs) {
                return getBytesCompressed(lobID, offset, length, false);
            } else {
                return getBytesNormal(lobID, offset, length);
            }
        } finally {
            writeLock.unlock();
        }
    }
