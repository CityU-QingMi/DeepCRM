    private Result setBytesBACompressed(long lobID, long offset,
                                        byte[] dataBytes, int dataLength,
                                        boolean isClob) {

        if (dataLength == 0) {
            return ResultLob.newLobSetResponse(lobID, 0);
        }

        if (dataLength <= largeLobBlockSize) {
            return setBytesBACompressedPart(lobID, offset, dataBytes,
                                            dataLength, isClob);
        }

        HsqlByteArrayInputStream is = new HsqlByteArrayInputStream(dataBytes,
            0, dataLength);

        return setBytesISCompressed(lobID, is, dataLength, isClob);
    }
