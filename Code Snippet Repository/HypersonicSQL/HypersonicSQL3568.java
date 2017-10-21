    public Object convertToDefaultType(SessionInterface session, Object a) {

        if (a == null) {
            return a;
        }

        if (a instanceof byte[]) {
            BinaryData data = new BinaryData((byte[]) a, ((byte[]) a).length);

            return convertToTypeLimits(session, data);
        } else if (a instanceof BinaryData) {
            return convertToTypeLimits(session, a);
        } else if (a instanceof String) {
            return convertToType(session, a, Type.SQL_VARCHAR);
        } else if (a instanceof Boolean) {
            return convertToType(session, a, Type.SQL_BOOLEAN);
        } else if (a instanceof Integer) {
            return convertToType(session, a, Type.SQL_INTEGER);
        } else if (a instanceof Long) {
            return convertToType(session, a, Type.SQL_BIGINT);
        } else if (a instanceof BitSet) {
            BitSet bs    = (BitSet) a;
            byte[] bytes = new byte[bs.size() / Byte.SIZE];

            for (int i = 0; i < bs.size(); i++) {
                boolean set = bs.get(i);

                if (set) {
                    BitMap.set(bytes, i);
                }
            }

            return new BinaryData(bytes, bs.size());
        }

        throw Error.error(ErrorCode.X_22501);
    }
