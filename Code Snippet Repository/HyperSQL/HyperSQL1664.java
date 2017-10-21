    public void write(RowOutputInterface out, LongLookup lookup) {

        int capacity = tableIds.length;

        out.setStorageSize(storageSize);

        for (int i = 0; i < capacity; i++) {
            out.writeInt(tableIds[i]);
        }

        for (int i = 0; i < capacity; i++) {
            out.writeInt(bitmapAddress[i]);
        }

        for (int i = 0; i < capacity; i++) {
            out.writeChar(freeSpace[i]);
        }

        for (int i = 0; i < capacity; i++) {
            out.writeChar(freeSpaceBlock[i]);
        }

        out.writeEnd();
    }
