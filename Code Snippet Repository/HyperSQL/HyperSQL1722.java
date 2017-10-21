    public Result setBytes(long lobID, long offset, byte[] dataBytes,
                           int dataLength) {

        if (byteBuffer == null) {
            throw Error.error(ErrorCode.DATA_IS_READONLY);
        }

        writeLock.lock();

        try {
            Object[] data = getLobHeader(lobID);

            if (data == null) {
                return Result.newErrorResult(Error.error(ErrorCode.X_0F502));
            }

            long length = ((Long) data[LOB_IDS.LOB_LENGTH]).longValue();

            if (dataLength == 0) {
                return ResultLob.newLobSetResponse(lobID, length);
            }

            Result result = setBytesBA(lobID, offset, dataBytes, dataLength,
                                       false);

            if (result.isError()) {
                return result;
            }

            if (offset + dataLength > length) {
                length = offset + dataLength;
                result = setLength(lobID, length);

                if (result.isError()) {
                    return result;
                }
            }

            return ResultLob.newLobSetResponse(lobID, length);
        } finally {
            writeLock.unlock();
        }
    }
