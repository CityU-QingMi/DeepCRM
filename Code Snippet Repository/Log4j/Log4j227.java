    private void readObject(final java.io.ObjectInputStream s)  throws IOException, ClassNotFoundException {
        // Read in the threshold (ignored), and any hidden stuff
        s.defaultReadObject();

        // set other fields that need values
        keys = EMPTY;
        values = EMPTY;

        // Read in number of buckets
        final int capacity = s.readInt();
        if (capacity < 0) {
            throw new InvalidObjectException("Illegal capacity: " + capacity);
        }

        // Read number of mappings
        final int mappings = s.readInt();
        if (mappings < 0) {
            throw new InvalidObjectException("Illegal mappings count: " + mappings);
        }

        // allocate the bucket array;
        if (mappings > 0) {
            inflateTable(capacity);
        } else {
            threshold = capacity;
        }

        // Read the keys and values, and put the mappings in the arrays
        for (int i = 0; i < mappings; i++) {
            keys[i] = (String) s.readObject();
            try {
                final byte[] marshalledObject = (byte[]) s.readObject();
                values[i] = marshalledObject == null ? null : unmarshall(marshalledObject);
            } catch (final Exception | LinkageError error) {
                handleSerializationException(error, i, keys[i]);
                values[i] = null;
            }
        }
        size = mappings;
    }
