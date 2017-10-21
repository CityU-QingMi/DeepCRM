    public Result setChars(long lobID, long offset, char[] chars,
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

            byte[] bytes = ArrayUtil.charArrayToBytes(chars, dataLength);
            Result result = setBytesBA(lobID, offset * 2, bytes,
                                       dataLength * 2, true);

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
