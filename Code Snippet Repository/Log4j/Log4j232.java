    private void merge(final SortedArrayStringMap other) {
        final String[] myKeys = keys;
        final Object[] myVals = values;
        final int newSize = other.size + this.size;
        threshold = ceilingNextPowerOfTwo(newSize);
        if (keys.length < threshold) {
            keys = new String[threshold];
            values = new Object[threshold];
        }
        // move largest collection to the beginning of this data structure, smallest to the end
        boolean overwrite = true;
        if (other.size() > size()) {
            // move original key-values to end
            System.arraycopy(myKeys, 0, keys, other.size, this.size);
            System.arraycopy(myVals, 0, values, other.size, this.size);

            // insert additional key-value pairs at the beginning
            System.arraycopy(other.keys, 0, keys, 0, other.size);
            System.arraycopy(other.values, 0, values, 0, other.size);
            size = other.size;

            // loop over original keys and insert (drop values for same key)
            overwrite = false;
        } else {
            System.arraycopy(myKeys, 0, keys, 0, this.size);
            System.arraycopy(myVals, 0, values, 0, this.size);

            // move additional key-value pairs to end
            System.arraycopy(other.keys, 0, keys, this.size, other.size);
            System.arraycopy(other.values, 0, values, this.size, other.size);

            // new values are at the end, will be processed below. Overwrite is true.
        }
        for (int i = size; i < newSize; i++) {
            final int index = indexOfKey(keys[i]);
            if (index < 0) { // key does not exist
                insertAt(~index, keys[i], values[i]);
            } else if (overwrite) { // existing key: only overwrite when looping over the new values
                keys[index] = keys[i];
                values[index] = values[i];
            }
        }
        // prevent memory leak: null out references
        Arrays.fill(keys, size, newSize, null);
        Arrays.fill(values, size, newSize, null);
    }
