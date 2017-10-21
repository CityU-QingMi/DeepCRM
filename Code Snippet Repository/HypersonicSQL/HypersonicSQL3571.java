    public long position(SessionInterface session, BlobData data,
                         BlobData otherData, Type otherType, long offset) {

        if (data == null || otherData == null) {
            return -1L;
        }

        long otherLength = data.bitLength(session);

        if (offset + otherLength > data.bitLength(session)) {
            return -1;
        }

        throw Error.runtimeError(ErrorCode.U_S0500, "BitType");
    }
