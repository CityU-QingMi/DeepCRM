    public void storeFragment(ByteBuffer fragment, int length, boolean last)
    {
        if (storage == null)
        {
            int space = last ? length : length * 2;
            storage = ByteBuffer.allocate(space);
        }

        // Grow the storage if necessary.
        if (storage.remaining() < length)
        {
            int space = last ? length : length * 2;
            int capacity = storage.position() + space;
            ByteBuffer newStorage = ByteBuffer.allocate(capacity);
            storage.flip();
            newStorage.put(storage);
            storage = newStorage;
        }

        // Copy the fragment into the storage.
        int limit = fragment.limit();
        fragment.limit(fragment.position() + length);
        storage.put(fragment);
        fragment.limit(limit);
    }
