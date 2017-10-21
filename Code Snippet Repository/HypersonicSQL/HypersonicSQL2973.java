    public Object[] readData(Type[] colTypes) {

        if (crypto != null) {
            int start = pos;
            int size  = readInt();

            crypto.decode(buffer, pos, size, buffer, start);

            pos = start;
        }

        return super.readData(colTypes);
    }
