    public static int countSetBitsEnd(int map) {

        int mask  = 0x01;
        int count = 0;

        for (; count < Integer.SIZE; count++) {
            if ((map & mask) == 0) {
                break;
            }

            map >>= 1;
        }

        return count;
    }
