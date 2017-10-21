    private void writeObject(final java.io.ObjectOutputStream s) throws IOException {
        // Write out the threshold, and any hidden stuff
        s.defaultWriteObject();

        // Write out number of buckets
        if (keys == EMPTY) {
            s.writeInt(ceilingNextPowerOfTwo(threshold));
        } else {
            s.writeInt(keys.length);
        }

        // Write out size (number of Mappings)
        s.writeInt(size);

        // Write out keys and values (alternating)
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                s.writeObject(keys[i]);
                try {
                    s.writeObject(marshall(values[i]));
                } catch (final Exception e) {
                    handleSerializationException(e, i, keys[i]);
                    s.writeObject(null);
                }
            }
        }
    }
