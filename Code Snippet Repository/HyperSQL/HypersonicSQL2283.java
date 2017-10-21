    public static boolean isSet(byte[] map, int pos) {

        int mask  = 0x00000080 >>> (pos & 0x07);
        int index = pos / 8;

        if (index >= map.length) {
            return false;
        }

        byte b = map[index];

        return (b & mask) == 0 ? false
                               : true;
    }
