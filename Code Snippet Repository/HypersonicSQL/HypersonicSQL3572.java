    public BlobData substring(SessionInterface session, BlobData data,
                              long offset, long length, boolean hasLength) {

        long end;
        long dataLength = data.bitLength(session);

        if (hasLength) {
            end = offset + length;
        } else {
            end = dataLength > offset ? dataLength
                                      : offset;
        }

        if (end < offset) {
            throw Error.error(ErrorCode.X_22011);
        }

        if (offset > end || end < 0) {

            // return zero length data
            offset = 0;
            end    = 0;
        }

        if (offset < 0) {
            offset = 0;
        }

        if (end > dataLength) {
            end = dataLength;
        }

        length = end - offset;

        byte[] dataBytes = data.getBytes();
        byte[] bytes     = new byte[(int) (length + 7) / 8];

        for (int i = (int) offset; i < end; i++) {
            if (BitMap.isSet(dataBytes, i)) {
                BitMap.set(bytes, i - (int) offset);
            }
        }

        return new BinaryData(bytes, length);
    }
