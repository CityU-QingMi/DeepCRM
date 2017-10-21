    public Result setBytesForNewBlob(long lobID, InputStream inputStream,
                                     long length) {

        if (length == 0) {
            return ResultLob.newLobSetResponse(lobID, 0);
        }

        if (byteBuffer == null) {
            throw Error.error(ErrorCode.DATA_IS_READONLY);
        }

        writeLock.lock();

        try {
            Result result = setBytesIS(lobID, inputStream, length, false);

            return result;
        } finally {
            writeLock.unlock();
        }
    }
