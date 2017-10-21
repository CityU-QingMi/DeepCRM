    public BlobData overlay(Session session, BlobData value, BlobData overlay,
                            long offset, long length, boolean hasLength) {

        if (value == null || overlay == null) {
            return null;
        }

        if (!hasLength) {
            length = overlay.bitLength(session);
        }

        switch (typeCode) {

            case Types.SQL_BIT :
            case Types.SQL_BIT_VARYING : {
                byte[] data =
                    (byte[]) ArrayUtil.duplicateArray(value.getBytes());
                byte[] overlaydata = overlay.getBytes();

                for (int i = 0, pos = (int) offset; i < length;
                        pos += 8, i++) {
                    int count = 8;

                    if (length - pos < 8) {
                        count = (int) length - pos;
                    }

                    BitMap.overlay(data, pos, overlaydata[i], count);
                }

                BinaryData binary = new BinaryData(data,
                                                   value.bitLength(session));

                return binary;
            }
            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "BitType");
        }
    }
