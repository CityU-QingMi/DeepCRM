    public int getSize(Object[] data, int l, Type[] types) {

        int s = 0;

        for (int i = 0; i < l; i++) {
            Object o = data[i];

            s += getSize(o, types[i]);
        }

        return s;
    }
