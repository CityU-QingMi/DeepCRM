    public Result getChars(long lobID, long offset, int length) {

        Result result;

        writeLock.lock();

        try {
            if (compressLobs || cryptLobs) {
                result = getBytesCompressed(lobID, offset * 2, length * 2,
                                            true);
            } else {
                result = getBytesNormal(lobID, offset * 2, length * 2);
            }
        } finally {
            writeLock.unlock();
        }

        if (result.isError()) {
            return result;
        }

        byte[] bytes = ((ResultLob) result).getByteArray();
        char[] chars = ArrayUtil.byteArrayToChars(bytes);

        return ResultLob.newLobGetCharsResponse(lobID, offset, chars);
    }
