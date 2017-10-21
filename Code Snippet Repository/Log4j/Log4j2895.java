        public static long nextPowerOfTwo(long x) {
            if (x == 0) {
                return 1;
            }
            x--;
            x |= x >> 1;
            x |= x >> 2;
            x |= x >> 4;
            x |= x >> 8;
            x |= x >> 16;
            return (x | x >> 32) + 1;
        }
