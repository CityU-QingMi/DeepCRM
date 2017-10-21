    public static byte[] toByteArray(long hi, long lo) {

        byte[] bytes = new byte[16];
        int    count = 0;
        int    v;

        while (count < 16) {
            if (count == 0) {
                v = (int) (hi >>> 32);
            } else if (count == 4) {
                v = (int) hi;
            } else if (count == 8) {
                v = (int) (lo >>> 32);
            } else {
                v = (int) lo;
            }

            bytes[count++] = (byte) (v >>> 24);
            bytes[count++] = (byte) (v >>> 16);
            bytes[count++] = (byte) (v >>> 8);
            bytes[count++] = (byte) v;
        }

        return bytes;
    }
