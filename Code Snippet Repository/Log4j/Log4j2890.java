    @SuppressWarnings("")
    private void initFrom0(final OpenHashStringMap other) {
        // this.loadFactor = other.loadFactor; // final field
        this.arraySize = other.arraySize;
        this.size = other.size;
        this.containsNullKey = other.containsNullKey;
        this.mask = other.mask;
        this.maxFill = other.maxFill;
        keys = (K[]) Arrays.copyOf(other.keys, arraySize + 1);
        values = (V[]) Arrays.copyOf(other.values, arraySize + 1);
    }
