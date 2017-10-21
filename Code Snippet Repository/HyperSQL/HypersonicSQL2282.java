    public static int setByte(int map, byte value, int pos) {

        int intValue = (value & 0xff) << (24 - pos);
        int mask     = 0xff000000 >>> pos;

        mask = ~mask;
        map  &= mask;

        return (map | intValue);
    }
