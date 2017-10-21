    public void write(RowOutputInterface out, LongLookup lookup) {

        int capacity = values.length;

        out.setStorageSize(storageSize);

        for (int i = 0; i < capacity; i++) {
            out.writeInt(values[i]);
        }

        out.writeEnd();
    }
