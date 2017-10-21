    public void write(RowOutputInterface out, LongLookup lookup) {

        int[] array    = bitMap.getIntArray();
        int   capacity = array.length;

        out.setStorageSize(storageSize);

        for (int i = 0; i < capacity; i++) {
            out.writeInt(array[i]);
        }

        out.writeEnd();
    }
