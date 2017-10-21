    protected void writeArray(Object[] o, Type type) {

        type = type.collectionBaseType();

        writeInt(o.length);

        for (int i = 0; i < o.length; i++) {
            writeData(o[i], type);
        }
    }
