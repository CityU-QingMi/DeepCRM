    protected Object[] readArray(Type type) {

        type = type.collectionBaseType();

        int      size = readInt();
        Object[] data = new Object[size];

        for (int i = 0; i < size; i++) {
            data[i] = readData(type);
        }

        return data;
    }
