    public void writeData(Row row, Type[] types) {

        if (crypto == null) {
            super.writeData(row, types);
        } else {
            int start = count;

            ensureRoom(row.getStorageSize());
            writeInt(0);
            super.writeData(row, types);

            int origLength = count - start - INT_STORE_SIZE;
            int newLength = crypto.encode(buffer, start + INT_STORE_SIZE,
                                          origLength, buffer,
                                          start + INT_STORE_SIZE);

            writeIntData(newLength, start);

            count = start + INT_STORE_SIZE + newLength;
        }
    }
