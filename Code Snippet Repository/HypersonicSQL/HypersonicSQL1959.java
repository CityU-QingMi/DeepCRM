    public boolean add(Object key, Object value) {

        int lookup = getLookup(key, key.hashCode());

        if (lookup >= 0) {
            return false;
        }

        super.put(key, value);

        return true;
    }
