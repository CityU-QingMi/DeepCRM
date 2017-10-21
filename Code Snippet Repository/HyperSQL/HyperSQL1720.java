    private Result setBytesBA(long lobID, long offset, byte[] dataBytes,
                              int dataLength, boolean isClob) {

        if (dataLength == 0) {
            return ResultLob.newLobSetResponse(lobID, 0);
        }

        writeLock.lock();

        try {
            if (compressLobs || cryptLobs) {
                return setBytesBACompressed(lobID, offset, dataBytes,
                                            dataLength, isClob);
            } else {
                return setBytesBANormal(lobID, offset, dataBytes, dataLength);
            }
        } finally {
            writeLock.unlock();
        }
    }
