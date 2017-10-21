    public Object remove(int key) {

        int lookup = getLookup(key);

        if (lookup < 0) {
            return null;
        }

        Object returnValue = super.addOrRemove(key, null, null, true);

        removeRow(lookup);

        return returnValue;
    }
