    public int getSize(Row row) {

        int size = super.getSize(row);

        if (crypto != null) {
            size = crypto.getEncodedSize(size - INT_STORE_SIZE)
                   + INT_STORE_SIZE * 2;
        }

        return size;
    }
