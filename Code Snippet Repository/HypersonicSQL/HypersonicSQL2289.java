    public static void overlay(byte[] map, int pos, byte source, int count) {

        int shift     = pos & 0x07;
        int mask      = (source & 0xff) >>> shift;
        int innermask = 0xff >> shift;
        int index     = pos / 8;

        if (count < 8) {
            innermask = innermask >>> (8 - count);
            innermask = innermask << (8 - count);
        }

        mask      &= innermask;
        innermask = ~innermask;

        if (index >= map.length) {
            return;
        }

        byte b = map[index];

        b          = (byte) (b & innermask);
        map[index] = (byte) (b | mask);

        if (shift == 0) {
            return;
        }

        shift = 8 - shift;

        if (count > shift) {
            mask           = ((source & 0xff) << 8) >>> shift;
            innermask      = 0xff00 >>> shift;
            innermask      = ~innermask;
            b              = map[index + 1];
            b              = (byte) (b & innermask);
            map[index + 1] = (byte) (b | mask);
        }
    }
