    public void writeSize(int size) {

        if (sizePosition < 0) {
            sizePosition = count;

            writeInt(size);
        } else {
            writeIntData(size, sizePosition);
        }

        storageSize = size;
    }
