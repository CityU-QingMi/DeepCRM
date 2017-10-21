    public static long getBinaryNormalisedCeiling(long value, int scale) {

        long mask    = 0xffffffffffffffffL << scale;
        long newSize = value & mask;

        if (newSize != value) {
            newSize = newSize + (1L << scale);
        }

        return newSize;
    }
