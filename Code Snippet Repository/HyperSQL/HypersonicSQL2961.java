    public Object[] readData(Type[] colTypes) {

        int      l    = colTypes.length;
        Object[] data = new Object[l];

        for (int i = 0; i < l; i++) {
            Type type = colTypes[i];

            data[i] = readData(type);
        }

        return data;
    }
