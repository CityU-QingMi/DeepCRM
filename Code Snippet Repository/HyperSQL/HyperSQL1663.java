    public void read(RowInputInterface in) {

        this.position = in.getFilePosition();

        int capacity = tableIds.length;

        for (int i = 0; i < capacity; i++) {
            tableIds[i] = in.readInt();
        }

        for (int i = 0; i < capacity; i++) {
            bitmapAddress[i] = in.readInt();
        }

        for (int i = 0; i < capacity; i++) {
            freeSpace[i] = in.readChar();
        }

        for (int i = 0; i < capacity; i++) {
            freeSpaceBlock[i] = in.readChar();
        }

        hasChanged = false;
    }
