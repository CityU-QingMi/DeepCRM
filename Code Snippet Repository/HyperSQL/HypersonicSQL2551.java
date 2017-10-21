    public Result setCharsForNewClob(long lobID, InputStream inputStream,
                                     long length) {

        if (length == 0) {
            return ResultLob.newLobSetResponse(lobID, 0);
        }

        if (byteBuffer == null) {
            throw Error.error(ErrorCode.DATA_IS_READONLY);
        }

        writeLock.lock();

        try {
            Result result = setBytesIS(lobID, inputStream, length * 2, false);

            if (result.isError()) {
                return result;
            }

            long newLength = ((ResultLob) result).getBlockLength();

            if (newLength < length) {
                Result trunc = truncate(lobID, newLength);
            }

            return result;
        } finally {
            writeLock.unlock();
        }
    }
