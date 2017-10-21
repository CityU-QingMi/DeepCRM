    public boolean addAll(Object[] keys) {

        boolean changed = false;

        for (int i = 0; i < keys.length; i++) {
            changed |= add(keys[i]);
        }

        return changed;
    }
