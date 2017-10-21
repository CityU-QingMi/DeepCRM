    public int hashCode(Object a) {

        if (a == null) {
            return 0;
        }

        int      hash  = 0;
        Object[] array = (Object[]) a;

        for (int i = 0; i < dataTypes.length && i < 4; i++) {
            hash += dataTypes[i].hashCode(array[i]);
        }

        return hash;
    }
