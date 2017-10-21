    public boolean addAll(Collection c) {

        boolean  changed = false;
        Iterator it      = c.iterator();

        while (it.hasNext()) {
            changed |= add(it.next());
        }

        return changed;
    }
