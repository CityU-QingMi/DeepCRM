    public Object remove(Object key) {

        int lookup = getLookup(key, key.hashCode());

        if (lookup < 0) {
            return null;
        }

        Object returnValue = super.remove(key);

        removeRow(lookup);

        return returnValue;
    }
