    public static long getBinaryNormalisedCeiling(long value) {

        long newSize = 2;

        while (newSize < value) {
            newSize <<= 1;
        }

        return newSize;
    }
