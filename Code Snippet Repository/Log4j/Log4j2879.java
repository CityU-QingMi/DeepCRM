    @SuppressWarnings("")
    public OpenHashStringMap(final int expected, final float f) {
        if (f <= 0 || f > 1) {
            throw new IllegalArgumentException(
                    "Load factor must be greater than 0 and smaller than or equal to 1");
        }
        if (expected < 0){
            throw new IllegalArgumentException(
                    "The expected number of elements must be nonnegative");
        }
        this.loadFactor = f;
        arraySize = HashCommon.arraySize(expected, f);
        mask = arraySize - 1;
        maxFill = HashCommon.maxFill(arraySize, f);
        keys = (K[]) new Object[arraySize + 1];
        values = (V[]) new Object[arraySize + 1];
    }
