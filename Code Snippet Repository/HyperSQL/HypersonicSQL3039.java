    protected void writeArray(Object[] o, Type type) {

        type         = type.collectionBaseType();
        noSeparators = true;

        write(BYTES_ARRAY);

        for (int i = 0; i < o.length; i++) {
            if (i > 0) {
                write(',');
            }

            writeData(o[i], type);
        }

        write(']');

        noSeparators = false;
    }
