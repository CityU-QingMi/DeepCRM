    private void initFrom0(final SortedArrayStringMap other) {
        if (keys.length < other.size) {
            keys = new String[other.threshold];
            values = new Object[other.threshold];
        }
        System.arraycopy(other.keys, 0, keys, 0, other.size);
        System.arraycopy(other.values, 0, values, 0, other.size);

        size = other.size;
        threshold = other.threshold;
    }
