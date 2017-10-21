    public long getLongColumnValue(Object value, int compareType) {

        if (value == null) {
            return Long.MIN_VALUE;
        }

        BinaryData binary    = (BinaryData) value;
        byte[]     bytes     = binary.getBytes();
        long       longValue = 0;

        for (int i = 0; i < 8; i++) {
            longValue |= (bytes[i] & 0x000000ff);

            if (i == 7) {
                break;
            }

            longValue <<= 8;
        }

        return longValue + Long.MIN_VALUE;
    }
