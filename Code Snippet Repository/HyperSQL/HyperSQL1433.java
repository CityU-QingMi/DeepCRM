    public static void or(byte[] map, int pos, byte source, int count) {

        int shift = pos & 0x07;
        int mask  = (source & 0xff) >>> shift;
        int index = pos / 8;

        if (index >= map.length) {
            return;
        }

        byte b = (byte) (map[index] | mask);

        map[index] = b;

        if (shift == 0) {
            return;
        }

        shift = 8 - shift;

        if (count > shift) {
            mask           = ((source & 0xff) << 8) >>> shift;
            b              = (byte) (map[index + 1] | mask);
            map[index + 1] = b;
        }
    }
