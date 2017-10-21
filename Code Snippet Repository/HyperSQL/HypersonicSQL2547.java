    private Result setBytesIS(long lobID, InputStream inputStream,
                              long length, boolean isClob) {

        if (length == 0) {
            return ResultLob.newLobSetResponse(lobID, 0);
        }

        if (compressLobs || cryptLobs) {
            return setBytesISCompressed(lobID, inputStream, length, isClob);
        } else {
            return setBytesISNormal(lobID, inputStream, length);
        }
    }
