    public boolean addAll(Object[] keys, int start, int limit) {

        boolean changed = false;

        for (int i = start; i < keys.length && i < limit; i++) {
            changed |= add(keys[i]);
        }

        return changed;
    }
