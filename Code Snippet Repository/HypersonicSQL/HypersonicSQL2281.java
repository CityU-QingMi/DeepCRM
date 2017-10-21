    public static int countUnsetBitsStart(int map) {

        int mask  = 0x80000000;
        int count = 0;

        if (map == 0) {
            return Integer.SIZE;
        }

        for (; count < Integer.SIZE; count++) {
            if ((map & mask) != 0) {
                break;
            }

            mask >>>= 1;
        }

        return count;
    }
