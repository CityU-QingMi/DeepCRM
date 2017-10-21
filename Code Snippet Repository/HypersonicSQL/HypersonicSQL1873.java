    public static long getBinaryMultipleCeiling(long value, long unit) {

        long newSize = value & -unit;

        if (newSize != value) {
            newSize += unit;
        }

        return newSize;
    }
