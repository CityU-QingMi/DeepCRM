    public int compare(Session session, Object a, Object b) {

        if (a == b) {
            return 0;
        }

        if (a == null) {
            return -1;
        }

        if (b == null) {
            return 1;
        }

        if (a instanceof BinaryData && b instanceof BinaryData) {
            byte[] data1  = ((BinaryData) a).getBytes();
            byte[] data2  = ((BinaryData) b).getBytes();
            int    length = data1.length > data2.length ? data2.length
                                                        : data1.length;

            for (int i = 0; i < length; i++) {
                if (data1[i] == data2[i]) {
                    continue;
                }

                return (((int) data1[i]) & 0xff) > (((int) data2[i]) & 0xff)
                       ? 1
                       : -1;
            }

            if (data1.length == data2.length) {
                return 0;
            }

            return data1.length > data2.length ? 1
                                               : -1;
        }

        throw Error.runtimeError(ErrorCode.U_S0500, "BinaryUUIDType");
    }
