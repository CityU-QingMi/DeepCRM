    public boolean removeAll(Object[] keys) {

        boolean result = true;

        for (int i = 0; i < keys.length; i++) {
            result &= remove(keys[i]);
        }

        return result;
    }
